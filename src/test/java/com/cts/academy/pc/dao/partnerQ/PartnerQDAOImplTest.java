package com.cts.academy.pc.dao.partnerQ;

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

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 *  Class for
 *  Testing for PartnerQDAO implementation with Real LDAP Instance
 *
 * @author polun.piotr
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource(value = "classpath:app_test.properties",properties = {"ldap.password=cts_academy"})
public class PartnerQDAOImplTest {

    public int TEST_EXISTING_PARTNER_ID = 10;
    public int TEST_NEW_PARTNER_ID = 5000;
    public int TEST_NOT_EXISTING_PARTNER_ID = 1000;
    public String TEST_DN = "partnerId=5000,dc=publishing,dc=cts-academy,dc=com";

    @Autowired
    ApplicationContext ctx;
    PartnerQDAOImpl dao;
    PartnerQ entity;

    @Before
    public void setUp() throws Exception {
        dao = ctx.getBean(PartnerQDAOImpl.class);
        entity = new PartnerQ();
        entity.setPartnerId(TEST_NEW_PARTNER_ID);
        entity.setStartTick(1);
        entity.setEndTick(0);
        dao.addPartnerQ(entity);
    }

    @Test
    public void buildDn() throws InvalidNameException {
        assertEquals(TEST_DN, dao.buildDn(TEST_NEW_PARTNER_ID).toString());
    }

    @Test
    public void getPartnerQPossitive() throws NamingException {
        assertEquals(TEST_EXISTING_PARTNER_ID, dao.getPartnerQ(TEST_EXISTING_PARTNER_ID).getPartnerId());
    }

    @Test(expected = NamingException.class)
    public void getPartnerQNegative() throws NamingException {
        dao.getPartnerQ(TEST_NOT_EXISTING_PARTNER_ID).getPartnerId();
    }

    @Test
    public void addPartnerQPossitive() throws NamingException {
        entity.setPartnerId(TEST_NEW_PARTNER_ID);
        dao.deletePartnerQ(entity);
        dao.addPartnerQ(entity);
        assertEquals(entity.getPartnerId(), dao.getPartnerQ(entity.getPartnerId()).getPartnerId());;
    }

    @Test(expected = NameAlreadyBoundException.class)
    public void addPartnerQNegative() throws NamingException {
        entity.setPartnerId(TEST_NEW_PARTNER_ID);
        dao.addPartnerQ(entity);
    }

    @Test
    public void modifyPartnerQPossitive() throws NamingException {
            entity.setStartTick(777);
            dao.modifyPartnerQ(entity);
            assertEquals(entity.getStartTick(), dao.getPartnerQ(entity.getPartnerId()).getStartTick());
    }

    @Test(expected = NameNotFoundException.class)
    public void modifyPartnerQNegative() throws NamingException {
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        entity.setStartTick(777);
        dao.modifyPartnerQ(entity);
        assertEquals(entity.getStartTick(), dao.getPartnerQ(entity.getPartnerId()).getStartTick());
    }

    @Test(expected = NameNotFoundException.class)
    public void deletePartnerPossitive() throws NamingException {
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        dao.addPartnerQ(entity);
        dao.deletePartnerQ(entity);
        dao.getPartnerQ(entity.getPartnerId());
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
    @Test (expected = NullPointerException.class)
    public void deletePartnerNegative() throws NamingException {
        dao.deletePartnerQ(null);
    }

    @After
    public void tearDown() throws Exception {
        entity.setPartnerId(TEST_NEW_PARTNER_ID);
        dao.deletePartnerQ(entity);
    }
}