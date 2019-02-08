package com.cts.academy.pc.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.AuthenticationException;
import javax.naming.ldap.LdapContext;
import java.util.Optional;


/**
 *  Class for
 *  Testing Connection to ldap , with wrong credentials
 *
 * @author vicol.valeriu
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppProperties.class})
@TestPropertySource(value = "classpath:application.properties",properties = {"ldap.password=wrong_pass"})
public class LdapContextTest {

    @Autowired
    ApplicationContext ctx;


    @Test(expected = BeanCreationException.class )
    public void testLdapContext() {
        LdapContext c = ctx.getBean(LdapContext.class);
    }
}
