package com.cts.academy.pc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;
import java.util.Optional;

import static com.cts.academy.pc.configuration.AppProperties.*;

/**
 *
 *  Class for creating connection to ldap
 *
 * @author valeriu.vicol
 */

@Repository
public class ConnectionManager {

    @Autowired
    AppProperties properties;

    @Bean
    @Scope("prototype")
    public InitialLdapContext ldapContext() throws NamingException {
        Hashtable<String,String> env = new Hashtable<>();

        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, properties.getLdapUrl());
        env.put(Context.SECURITY_PRINCIPAL, properties.getLdapDn());
        env.put(Context.SECURITY_CREDENTIALS, properties.getLdapPassword());


        return new InitialLdapContext(env,null);
    }

    @PostConstruct
    private void postConstruct(){

        Optional.ofNullable(properties.getDebugLevel()).ifPresent(debug->System.setProperty(DEBUG_LEVEL,debug));
        Optional.ofNullable(properties.getAuthenticationType()).ifPresent(type->System.setProperty(AUTHENTICATION,type));
        Optional.ofNullable(properties.getInitSize()).ifPresent(init->System.setProperty(CONNECTION_POOL_INIT_SIZE,init));
        Optional.ofNullable(properties.getPrefSize()).ifPresent(pref->System.setProperty(CONNECTION_POOL_PREF_SIZE,pref));
        Optional.ofNullable(properties.getMaxSize()).ifPresent(max->System.setProperty(CONNECTION_POOL_MAX_SIZE,max));
        Optional.ofNullable(properties.getLdapConnectionTimeout()).ifPresent(time->System.setProperty(CONNECTION_POOL_TIME_OUT,time));


    }


}
