package com.cts.academy.pc.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.*;

@ContextConfiguration(classes = {AppProperties.class})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.properties")
public class AppPropertiesTest {

    @Autowired
    AppProperties pro;


    @Test
    public void getLdapPasswordTest() {
        assertNotNull(pro.getLdapPassword());
    }

    @Test
    public void getLdapDnTest() {
        assertNotNull(pro.getLdapDn());
    }

    @Test
    public void getLdapUrlTest() {
        assertNotNull(pro.getLdapUrl());
    }

    @Test
    public void getLdapConnectionTimeoutTest() {
        assertNotNull(pro.getLdapConnectionTimeout());
    }

    @Test
    public void getLdapReadTimeoutTest() {
        assertNotNull(pro.getLdapReadTimeout());
    }

    @Test
    public void getConnectionPoolTimeoutTest() {
        assertNotNull(pro.getConnectionPoolTimeout());
    }

    @Test
    public void getConnectionPoolMaxSizeTest() {
        assertNotNull(pro.getConnectionPoolMaxSize());
    }

    @Test
    public void getConnectionPoolPreferredSizeTest() {
        assertNotNull(pro.getConnectionPoolPreferredSize());
    }

    @Test
    public void getConnectionPoolIdleTimeoutTest() {
        assertNotNull(pro.getConnectionPoolIdleTimeout());
    }

    @Test
    public void getLogSeverityTest() {
        assertNotNull(pro.getLogSeverity());
    }
}