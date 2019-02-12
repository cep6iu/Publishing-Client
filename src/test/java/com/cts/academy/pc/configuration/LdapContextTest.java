package com.cts.academy.pc.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 *  Class for
 *  Testing Connection to ldap , with wrong credentials
 *
 * @author vicol.valeriu
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource(value = "classpath:app_test.properties")
public class LdapContextTest {



    @Autowired
    ConnectionManager connectionManager;


    @Test
    public void testPoolingLdapContext() throws NamingException {
        LdapContext c = connectionManager.ldapContext();
        LdapContext c1 = connectionManager.ldapContext();
        assertNotEquals(c,c1);
    }

    public void testLdapContext() throws NamingException {
        LdapContext c = connectionManager.ldapContext();
        assertNotNull(c);
    }


}
