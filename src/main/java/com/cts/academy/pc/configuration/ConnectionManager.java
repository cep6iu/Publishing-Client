package com.cts.academy.pc.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;

import static com.cts.academy.pc.configuration.AppConfig.*;


/**
 * Class for creating connection to ldap
 *
 * @author valeriu.vicol
 */

@Component
public class ConnectionManager {


    @Autowired
    private Environment pro;


    @Autowired
    private Hashtable<String, String> env;

    @Value("${ldap.url}")
    private String url;

    @Value("${ldap.dn}")
    private String ldapDn;

    @Value("${ldap.password}")
    private String pass;



    @Bean
    public InitialLdapContext ldapContext() throws NamingException {
        return new InitialLdapContext(env, null);
    }


    /**
     *  set properties int Env for creating ldap Context
     */
    @PostConstruct
    private void postConstruct() {
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.SECURITY_PRINCIPAL, ldapDn);
        env.put(Context.SECURITY_CREDENTIALS, pass);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        env.put(POOLING, "true");
        env.put(LDAP_CONNECTION_TIME_OUT, pro.getProperty(LDAP_CONNECTION_TIME_OUT));
        env.put(LDAP_READ_TIME_OUT, pro.getProperty(LDAP_READ_TIME_OUT));


        System.setProperty(CONNECTION_POOL_PREF_SIZE, pro.getProperty(CONNECTION_POOL_PREF_SIZE));
        System.setProperty(CONNECTION_POOL_MAX_SIZE, pro.getProperty(CONNECTION_POOL_MAX_SIZE));
        System.setProperty(CONNECTION_POOL_INIT_SIZE, pro.getProperty(CONNECTION_POOL_INIT_SIZE));
        System.setProperty(CONNECTION_POOL_TIME_OUT, pro.getProperty(CONNECTION_POOL_TIME_OUT));

    }


}
