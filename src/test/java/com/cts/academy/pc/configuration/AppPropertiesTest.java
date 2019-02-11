package com.cts.academy.pc.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;


/**
 *  verify if properties loaded in AppConfiguration(Context) are the same witch are seted in .properties file
 *
 *  @author valeriu.vicol
 */

@ContextConfiguration(classes = {AppProperties.class})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:app_test.properties")
public class AppPropertiesTest {


    @Autowired
    AppProperties pro;


    @Autowired
    private Environment env;

    @Autowired
    LdapContext ldap;

    Class c;
    Field f;

    @Before
    public void setUp() throws Exception {
        c = pro.getClass().getSuperclass();
    }

    @Test
    public void ldapConnectionTest() throws NamingException {
        ldap.reconnect(ldap.getConnectControls());

    }


    @Test
    public void getLdapDnTest() throws IllegalAccessException, NoSuchFieldException {
        f = c.getDeclaredField("ldapDn");
        f.setAccessible(true);
        assertEquals(env.getProperty("ldap.dn"),f.get(pro));
    }

    @Test
    public void getLdapUrlTest() throws IllegalAccessException, NoSuchFieldException {
        f = c.getDeclaredField("ldapUrl");
        f.setAccessible(true);
        assertEquals(env.getProperty("ldap.url"),f.get(pro));
    }

    @Test
    public void getLdapConnectionTimeoutTest() throws NoSuchFieldException, IllegalAccessException {
        f = c.getDeclaredField("ldapConnectionTimeout");
        f.setAccessible(true);
        assertEquals(env.getProperty("com.sun.jndi.ldap.connect.pool.timeout"),f.get(pro));
    }

    @Test
    public void getLdapReadTimeoutTest() throws IllegalAccessException, NoSuchFieldException {
        f = c.getDeclaredField("ldapReadTimeout");
        f.setAccessible(true);
        assertEquals(env.getProperty("ldap.read.timeout"),f.get(pro));
    }

    @Test
    public void getConnectionPoolTimeoutTest() throws IllegalAccessException, NoSuchFieldException {
        f = c.getDeclaredField("poolConnectionTimeout");
        f.setAccessible(true);
        assertEquals(env.getProperty("com.sun.jndi.ldap.connect.pool.timeout"),f.get(pro));
    }

    @Test
    public void getConnectionPoolMaxSizeTest() throws NoSuchFieldException, IllegalAccessException {
        f = c.getDeclaredField("maxSize");
        f.setAccessible(true);
        assertEquals(env.getProperty("com.sun.jndi.ldap.connect.pool.maxsize"),f.get(pro));
    }

    @Test
    public void getConnectionPoolPreferredSizeTest() throws NoSuchFieldException, IllegalAccessException {
        f = c.getDeclaredField("prefSize");
        f.setAccessible(true);
        assertEquals(env.getProperty("com.sun.jndi.ldap.connect.pool.prefsize"),f.get(pro));
    }


    @Test
    public void getLogSeverityTest() throws NoSuchFieldException, IllegalAccessException {
        f = c.getDeclaredField("debugLevel");
        f.setAccessible(true);
        assertEquals(env.getProperty("com.sun.jndi.ldap.connect.pool.debug"),f.get(pro));
    }

    @Test
    public void getPassowrdTest() throws IllegalAccessException, NoSuchFieldException {
        f = c.getDeclaredField("ldapPassword");
        f.setAccessible(true);
        assertEquals(env.getProperty("ldap.password"),f.get(pro));
    }

    @Test
    public void getInitSize() throws NoSuchFieldException, IllegalAccessException {
        f = c.getDeclaredField("initSize");
        f.setAccessible(true);
        assertEquals(env.getProperty("com.sun.jndi.ldap.connect.pool.initsize"),f.get(pro));
    }

    @Test
    public void getAuthenticationTest() throws NoSuchFieldException, IllegalAccessException {
        f = c.getDeclaredField("authenticationType");
        f.setAccessible(true);
        assertEquals(env.getProperty("con.sun.jndi.ldap.connect.pool.authentication"),f.get(pro));
    }


}