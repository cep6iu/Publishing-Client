package com.cts.academy.pc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;
import java.util.Optional;

import static com.cts.academy.pc.configuration.AppConfig.*;


/**
 *
 *  Class for creating connection to ldap
 *
 * @author valeriu.vicol
 */

@Component
public class ConnectionManager {


    @Autowired
    Environment environment;


    @Value("${ldap.url}")
    private String url;


    @Value("${ldap.dn}")
    private String ldapDn;

    @Value("${ldap.password}")
    private String pass;


    @Bean
    @Scope("prototype")
    public InitialLdapContext ldapContext() throws NamingException {
        Hashtable<String,String> env = new Hashtable<>();

        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.SECURITY_PRINCIPAL, ldapDn);
        env.put(Context.SECURITY_CREDENTIALS,pass);


        return new InitialLdapContext(env,null);
    }

    @SuppressWarnings("Duplicates")
    @PostConstruct
    private void postConstruct(){

        Optional.ofNullable(environment.getProperty(DEBUG_LEVEL)).ifPresent(debug->System.setProperty(DEBUG_LEVEL,debug));
        Optional.ofNullable(environment.getProperty(AUTHENTICATION)).ifPresent(type->System.setProperty(AUTHENTICATION,type));
        Optional.ofNullable(environment.getProperty(CONNECTION_POOL_INIT_SIZE)).ifPresent(init->System.setProperty(CONNECTION_POOL_INIT_SIZE,init));
        Optional.ofNullable(environment.getProperty(CONNECTION_POOL_PREF_SIZE)).ifPresent(pref->System.setProperty(CONNECTION_POOL_PREF_SIZE,pref));
        Optional.ofNullable(environment.getProperty(CONNECTION_POOL_MAX_SIZE)).ifPresent(max->System.setProperty(CONNECTION_POOL_MAX_SIZE,max));
        Optional.ofNullable(environment.getProperty(CONNECTION_POOL_TIME_OUT)).ifPresent(time->System.setProperty(CONNECTION_POOL_TIME_OUT,time));


    }


}
