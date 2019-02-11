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

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:app_test.properties")
public class AppPropertiesTest {




    @Autowired
    private Environment env;

    @Test
    public void testDn() {
        assertEquals("cn=admin,dc=cts-academy,dc=com",env.getProperty("ldap.dn"));
    }

    @Test
    public void testPass() {
        assertEquals("test_password",env.getProperty("ldap.password"));
    }

    @Test
    public void testUrl() {
        assertEquals("ldap://192.168.99.100",env.getProperty("ldap.url"));
    }
}