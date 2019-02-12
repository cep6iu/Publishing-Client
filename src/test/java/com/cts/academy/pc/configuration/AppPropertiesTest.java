package com.cts.academy.pc.configuration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


/**
 * verify if properties loaded in AppConfiguration(Context) are the same witch are seted in .properties file
 *
 * @author valeriu.vicol
 */

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:app_test.properties")
public class AppPropertiesTest {


    @Autowired
    private Environment env;

    @Test
    public void testDn() {
        assertEquals("cn=admin,dc=cts-academy,dc=com", env.getProperty("ldap.dn"));
    }

    @Test
    public void testPass() {
        assertEquals("cts_academy", env.getProperty("ldap.password"));
    }

    @Test
    public void testUrl() {
        assertEquals("ldap://192.168.99.100:389", env.getProperty("ldap.url"));
        
    }

    @Test
    public void authenticationTest() {
        assertEquals("simple",env.getProperty("con.sun.jndi.ldap.connect.pool.authentication"));
    }


    @Test
    public void initSizeTest() {
        assertEquals("10",env.getProperty("com.sun.jndi.ldap.connect.pool.initsize"));
    }

    @Test
    public void maxSizeTest() {
        assertEquals("20",env.getProperty("com.sun.jndi.ldap.connect.pool.maxsize"));
    }

    @Test
    public void prefSizeTest() {
        assertEquals("15",env.getProperty("com.sun.jndi.ldap.connect.pool.prefsize"));
    }

    @Test
    public void connectionPoolTimeoutTest() {
        assertEquals("30000",env.getProperty("com.sun.jndi.ldap.connect.pool.timeout"));
    }

    @Test
    public void ldapReadTimeoutTest() {
        assertEquals("15000",env.getProperty("com.sun.jndi.ldap.read.timeout"));
    }

    @Test
    public void ldapConnectTimeoutTest() {
        assertEquals("30000",env.getProperty("com.sun.jndi.ldap.connect.timeout"));
    }

    @Test
    public void ldapPoolingTest(){
        assertEquals("true",env.getProperty("com.sun.jndi.ldap.connect.pool"));
    }
}