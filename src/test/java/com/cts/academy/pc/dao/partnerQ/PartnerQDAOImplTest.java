package com.cts.academy.pc.dao.partnerQ;

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


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource(value = "classpath:app_test.properties",properties = {"ldap.password=cts_academy"})
public class PartnerQDAOImplTest {

    public int TEST_EXISTING_PARTNER_ID = 10;
    public int TEST_NOT_EXISTING_PARTNER_ID = 1000;
    public String TEST_DN = "partnerId=10,dc=publishing,dc=cts-academy,dc=com";

    @Autowired
    ApplicationContext ctx;
    PartnerQDAOImpl dao;
    PartnerQ entity;

    @Before
    public void setUp() throws Exception {
        dao = ctx.getBean(PartnerQDAOImpl.class);
        entity = new PartnerQ();
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        entity.setStartTick(1);
        entity.setEndTick(0);
    }

    @Test
    public void buildDn() throws InvalidNameException {
        assertEquals(dao.buildDn(TEST_EXISTING_PARTNER_ID).toString(), TEST_DN );
    }

    @Test
    public void getPartnerQ() throws NamingException {
        assertEquals(dao.getPartnerQ(TEST_EXISTING_PARTNER_ID).getPartnerId(), TEST_EXISTING_PARTNER_ID );
    }

    @Test
    public void addPartnerQ() throws NamingException {
        dao.addPartnerQ(entity);
        assertEquals(dao.getPartnerQ(entity.getPartnerId()).getPartnerId(), entity.getPartnerId());
        dao.deletePartnerQ(entity);
    }

    @Test(expected = NameAlreadyBoundException.class)
    public void addExistingPartnerQ() throws NamingException {
        entity.setPartnerId(TEST_EXISTING_PARTNER_ID);
        dao.addPartnerQ(entity);
    }

    @Test
    public void modifyPartnerQ() throws NamingException {
            entity.setPartnerId(TEST_EXISTING_PARTNER_ID);
            entity.setStartTick(2);
            entity.setEndTick(0);
            dao.modifyPartnerQ(entity);
            assertEquals(dao.getPartnerQ(entity.getPartnerId()).getStartTick(), entity.getStartTick());
            entity.setStartTick(1);
            dao.modifyPartnerQ(entity);
    }

    @Test (expected = NamingException.class)
    public void wrongPartnerQ() throws NamingException {
        dao.getPartnerQ(TEST_NOT_EXISTING_PARTNER_ID).getPartnerId();
    }

    @Test (expected = NameNotFoundException.class)
    public void modifyNonExisting() throws NamingException {
        dao.modifyPartnerQ(entity);
    }

    @Test (expected = NameNotFoundException.class)
    public void deleteNonExisting() throws NamingException {
        entity.setPartnerId(TEST_NOT_EXISTING_PARTNER_ID);
        dao.addPartnerQ(entity);
        dao.deletePartnerQ(entity);
        dao.modifyPartnerQ(entity);
    }
}