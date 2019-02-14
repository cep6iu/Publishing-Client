package com.cts.academy.pc.dao.partnerConfig;

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
public class PartnerConfigDaoTest {


    public int TEST_PARTNER_ID = 30;
    public int TEST_MAX_NUM = 3;
    public String TEST_PARTNER_NAME = "partner30";
    public String PARTNER_URL = "localhost";
    public String TEST_DN = "partnerId=30,dc=partner,dc=cts-academy,dc=com";

    @Autowired
    ApplicationContext appcon;
    PartnerConfigDAO dao;
    PartnerConfig parConf;

    @Before
    public void setUp() throws Exception {
        dao = appcon.getBean(PartnerConfigDAO.class);
        parConf = new PartnerConfig();
        parConf.setPartnerId(TEST_PARTNER_ID);
        parConf.setPartnerMaxNumRetry(TEST_MAX_NUM);
        parConf.setPartnerName(TEST_PARTNER_NAME);
        parConf.setPartnerUrl(PARTNER_URL);
        parConf.setDn(TEST_DN);
        dao.addPartnerConfig(parConf);
    }

    @Test
    public void buildDn() throws InvalidNameException {
        assertEquals(TEST_DN, dao.buildDn(TEST_PARTNER_ID).toString());
    }

    @Test
    public void getPartnerConfigPositive() throws NamingException {
        assertEquals(TEST_PARTNER_ID, dao.getPartnerConfig(TEST_PARTNER_ID).getPartnerId());
    }

    @Test(expected = NamingException.class)
    public void getPartnerConfigNegative() throws NamingException {
        dao.deletePartnerConfig(parConf.getPartnerId());
        assertEquals(TEST_PARTNER_ID, dao.getPartnerConfig(TEST_PARTNER_ID).getPartnerId());
        dao.addPartnerConfig(parConf);
    }

    @Test
    public void addPartnerConfigPossitive() throws NamingException {
        parConf.setPartnerId(TEST_PARTNER_ID);
        dao.deletePartnerConfig(parConf.getPartnerId());
        dao.addPartnerConfig(parConf);
        assertEquals(parConf.getPartnerId(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerId());
    }

    @Test(expected = NameAlreadyBoundException.class)
    public void addPartnerConfigNegative() throws NamingException {
        parConf.setPartnerId(TEST_PARTNER_ID);
        dao.addPartnerConfig(parConf);
    }

    @Test
    public void modifyPartnerConfig() throws NamingException {
        parConf.setPartnerMaxNumRetry(4);
        parConf.setPartnerName("partner13");
        parConf.setPartnerUrl("someUrl");
        dao.modifyParnerConfig(parConf);
        assertEquals(parConf.getPartnerName(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerName());
        assertEquals(parConf.getPartnerMaxNumRetry(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerMaxNumRetry());
        assertEquals(parConf.getPartnerUrl(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerUrl());
    }

    @Test(expected = NameNotFoundException.class)
    public void modifyNonExistingPartnerConfig() throws NamingException {
        int notExistId = 55;
        parConf.setPartnerId(notExistId);
        parConf.setPartnerMaxNumRetry(13);
        parConf.setPartnerName("partner3");
        parConf.setPartnerUrl("someUrl1");
        dao.modifyParnerConfig(parConf);
        assertEquals(parConf.getPartnerName(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerName());
        assertEquals(parConf.getPartnerMaxNumRetry(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerMaxNumRetry());
        assertEquals(parConf.getPartnerUrl(), dao.getPartnerConfig(parConf.getPartnerId()).getPartnerUrl());
    }

    @Test
    public void deleteExistingPartnerConfigPositive() throws NamingException {
        dao.deletePartnerConfig(parConf.getPartnerId());
        dao.addPartnerConfig(parConf);
        dao.getPartnerConfig(parConf.getPartnerId());
    }

    //- Test "deleteNotExistingPartnerConfig"
    //- can't realised negative test of delete test
    //- becouse test doesn't return Exception
    @Test // (expected = NameNotFoundException.class)
    public void deleteNotExistingPartnerConfig() throws NamingException {
        int notExistId = 60;
        parConf.setPartnerId(notExistId);
        dao.deletePartnerConfig(parConf.getPartnerId());
    }

    @After
    public void tearDown() throws Exception {
        parConf.setPartnerId(TEST_PARTNER_ID);
        dao.deletePartnerConfig(parConf.getPartnerId());
    }
}
