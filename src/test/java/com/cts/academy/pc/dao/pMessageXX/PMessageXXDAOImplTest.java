package com.cts.academy.pc.dao.pMessageXX;

import com.cts.academy.pc.configuration.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.InvalidNameException;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import static org.junit.Assert.*;

/**
 *  Class for
 *  Testing for PartnerQDAO implementation with Real LDAP Instance
 *
 * @author polun.piotr
 */

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:app_test.properties",properties = {"ldap.password=cts_academy"})
public class PMessageXXDAOImplTest {

    public int TEST_NOT_EXISTING_PARTNER_ID= 60;
    public int TEST_BUCKET_ID = 0;
    public int TEST_NEW_MESSAGE_ID = 10000;
    public int TEST_PARTNER_ID = 10;
    public int TEST_RE_ENQ = 0;
    public String TEST_DN = "messageID=10000,tick=0,partnerID=10,dc=publishing,dc=cts-academy,dc=com";
    public String TEST_MESSAGE = "Some testing Message in here....";

    @Autowired
    ApplicationContext ctx;
    PMessageXXDAOImpl dao;
    PMessageXX entity;

    @Before
    public void setUp() throws Exception {
        dao = ctx.getBean(PMessageXXDAOImpl.class);
        entity = new PMessageXX();
        entity.setMessage(TEST_MESSAGE);
        entity.setMessageID(TEST_NEW_MESSAGE_ID);
        entity.setPartnerId(TEST_PARTNER_ID);
        entity.setBucketTick(TEST_BUCKET_ID);
        entity.setMessageReEnq(TEST_RE_ENQ);
        entity.setCustomerIdentifier("Mercedes-BENZ");
        entity.setDn(dao.buildDn(entity).toString());
        dao.addPMessageXX(entity);
    }

    @Test
    public void buildDn() throws InvalidNameException {
        assertEquals(TEST_DN, dao.buildDn(entity).toString());
    }

    @Test
    public void getMessageQPossitive() throws NamingException {
        assertEquals(TEST_NEW_MESSAGE_ID, dao.getPMessageXX(entity).getMessageID());
    }

    @Test(expected = NamingException.class)
    public void getMessageQNegative() throws NamingException {
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        assertEquals(TEST_NEW_MESSAGE_ID, dao.getPMessageXX(entity).getMessageID());
    }

    @Test
    public void addMessageQPossitive() throws NamingException {
        assertEquals(entity.getMessage(), dao.getPMessageXX(entity).getMessage());
    }

    @Test (expected = NameAlreadyBoundException.class)
    public void addMessageQNegative() throws NamingException {
        dao.addPMessageXX(entity);
    }

    @Test
    public void modifyMessageQPossitive() throws NamingException {
        dao.modifyPMessageXX(entity);
        assertEquals(entity.getMessageReEnq(), dao.getPMessageXX(entity).getMessageReEnq());
    }

   @Test(expected = NameNotFoundException.class)
    public void modifyMessageQNegative() throws NamingException {
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        dao.modifyPMessageXX(entity);
    }

    @Test(expected = NameNotFoundException.class)
    public void deletePartnerPossitive() throws NamingException {
        entity.setMessageID(TEST_NEW_MESSAGE_ID + 1);
        dao.addPMessageXX(entity);
        dao.deletePMessageXX(entity);
        assertEquals(TEST_NEW_MESSAGE_ID + 1, dao.getPMessageXX(entity).getMessageID());
    }

    /*
     * Delete using idempotent unbind function
     * It succeeds even if the terminal atomic name
     * is not bound in the target context, but throws
     * <tt>NameNotFoundException</tt>
     * if any of the intermediate contexts do not exist.
     *
     * ROOT_DN may be modified via REFLECTION but was considered as dangerous
     * so just testing null
     * */
    @Test (expected = NameNotFoundException.class)
    public void deletePartnerNegative() throws NamingException {
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        dao.deletePMessageXX(entity);
    }
    @After
    public void tearDown() throws Exception {
        entity.setMessageID(TEST_NEW_MESSAGE_ID);
        entity.setPartnerId(TEST_PARTNER_ID);
        dao.deletePMessageXX(entity);
    }

}