package com.cts.academy.pc.dao.pMessageXX;

import com.cts.academy.pc.configuration.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.InvalidNameException;
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

    public int TEST_EXISTING_PARTNER_ID = 10;
    public int TEST_NOT_EXISTING_PARTNER_ID = 100;
    public int TEST_BUCKET_ID = 0;
    public int TEST_NOT_EXISTING_MESSAGE_ID = 10;
    public int TEST_PARTNER_ID = 10;
    public String TEST_DN = "messageID=10,tick=0,partnerID=10,dc=publishing,dc=cts-academy,dc=com";
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
        entity.setMessageID(TEST_NOT_EXISTING_MESSAGE_ID);
        entity.setPartnerID(TEST_PARTNER_ID);
        entity.setBucketTick(TEST_BUCKET_ID);
        entity.setDn(dao.buildDn(entity).toString());
    }

    @Test
    public void buildDn() throws InvalidNameException {
        assertEquals(dao.buildDn(entity).toString(), TEST_DN );
    }

    @Test
    public void addPartnerQ() throws NamingException {
        dao.addPMessageXX(entity);
        assertEquals(dao.getPMessageXX(entity.getMessageID(), entity.getBucketTick(), entity.getPartnerID()).getMessage(), entity.getMessage());
        dao.deletePMessageXX(entity);
    }

    @Test (expected = NameNotFoundException.class)
    public void deleteNonExisting() throws NamingException {
        entity.setPartnerID(TEST_NOT_EXISTING_PARTNER_ID);
        dao.modifyPMessageXX(entity);
    }

}