package com.cts.academy.pc.dao.pMessageBucket;

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


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource(value = "classpath:app_test.properties", properties = {"ldap.password=cts_academy"})
public class MessageBucketDAOImplTest {
    public int TEST_PARTNER_ID = 10;
    public int TEST_TICK = 100;
    public String TEST_DN = "tick=100,partnerId=10,dc=publishing,dc=cts-academy,dc=com";

    @Autowired
    ApplicationContext ctx;
    MessageBucketDAOImpl dao;
    MessageBucket msgBuck;

    @Before
    public void setUp() throws Exception {
        dao = ctx.getBean(MessageBucketDAOImpl.class);
        msgBuck = new MessageBucket();
        msgBuck.setTick(TEST_TICK);
        msgBuck.setPartnerId(TEST_PARTNER_ID);
        msgBuck.setDn(TEST_DN);
        dao.addMsgBucketDAO(msgBuck);
    }

    @Test
    public void buildDn() throws InvalidNameException {
        assertEquals(TEST_DN, dao.buildDn(TEST_TICK, TEST_PARTNER_ID).toString());
    }

    @Test
    public void getMsgBucketDAOPositive() throws NamingException {
        assertEquals(msgBuck.getTick(), dao.getMsgBucketDAO(msgBuck.getTick(), msgBuck.getPartnerId()).getTick());
    }

    @Test(expected = NameNotFoundException.class)
    public void getMsgBucketDAONegative() throws NamingException {
        dao.deleteMsgBucketDAO(msgBuck);
        assertEquals(msgBuck.getTick(), dao.getMsgBucketDAO(msgBuck.getTick(), msgBuck.getPartnerId()).getTick());
    }

    @Test
    public void addMsgBucketDAOPositive() throws NamingException {
        dao.deleteMsgBucketDAO(msgBuck);
        dao.addMsgBucketDAO(msgBuck);
        assertEquals(msgBuck.getTick(), dao.getMsgBucketDAO(msgBuck.getTick(), msgBuck.getPartnerId()).getTick());
    }

    @Test(expected = NameAlreadyBoundException.class)
    public void addMsgBucketDAONegative() throws NamingException {
        dao.addMsgBucketDAO(msgBuck);
    }

    @Test(expected = NameNotFoundException.class)
    public void deleteExistingPositive() throws NamingException {
        dao.deleteMsgBucketDAO(msgBuck);
        dao.getMsgBucketDAO(msgBuck.getTick(), msgBuck.getPartnerId()).getTick();
    }

    @Test(expected = NameNotFoundException.class)
    public void deleteNotExistingNegative() throws NamingException {
        msgBuck.setPartnerId(11);
        dao.deleteMsgBucketDAO(msgBuck);
    }

    @After
    public void tearDown() throws Exception {
        msgBuck.setPartnerId(TEST_PARTNER_ID);
        dao.deleteMsgBucketDAO(msgBuck);
    }
}
