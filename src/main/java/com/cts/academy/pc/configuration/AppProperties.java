package com.cts.academy.pc.configuration;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;
import java.util.Optional;

/**
 * Class with properties of application
 *
 * @author valeriu.vicol
 * <p>
 * //command to set the source
 *  java -jar -Dapp.home="/home/mkyon/test" example.jar
 *
 *
 */

@PropertySource(value = {"file:${path}"},ignoreResourceNotFound = true)
@Configuration
public class AppProperties {

    /**
     *      static properties witch will set as system variables
     */
    private static final String AUTHENTICATION="con.sun.jndi.ldap.connect.pool.authentication";
    private static final String DEBUG_LEVEL="com.sun.jndi.ldap.connect.pool.debug";
    private static final String CONNECTION_POOL_INIT_SIZE="com.sun.jndi.ldap.connect.pool.initsize";
    private static final String CONNECTION_POOL_MAX_SIZE="com.sun.jndi.ldap.connect.pool.maxsize";
    private static final String CONNECTION_POOL_PREF_SIZE="com.sun.jndi.ldap.connect.pool.prefsize";
    private static final String CONNECTION_POOL_TIME_OUT="com.sun.jndi.ldap.connect.pool.timeout";


    //for creating new Ldap Context
    private Hashtable<String,String> env = new Hashtable<>();


    /**
     * Properties fields
     */

    @Value("${ldap.password}")
    private String ldapPassword;

    @Value("${ldap.dn}")
    private String ldapDn;

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${"+AUTHENTICATION+"}")
    private String authenticationType;

    @Value("${"+DEBUG_LEVEL+"}")
    private String debugLevel;

    @Value("${"+CONNECTION_POOL_INIT_SIZE+"}")
    private String initSize;

    @Value("${"+CONNECTION_POOL_MAX_SIZE+"}")
    private String maxSize;

    @Value("${"+CONNECTION_POOL_PREF_SIZE+"}")
    private String prefSize;

    @Value("${"+CONNECTION_POOL_TIME_OUT+"}")
    private String poolConnectionTimeout;

    @Value("${ldap.connect.timeout}")
    private String ldapConnectionTimeout;//#

    @Value("${ldap.read.timeout}")
    private String ldapReadTimeout;//#

    /**
    Extracting envroiment variables and save the into hashmap
     */

    @PostConstruct
    private void setValues() {
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_PRINCIPAL, ldapDn);
        env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");


        // Set as system properties
        //
        //  https://docs.oracle.com/javase/jndi/tutorial/ldap/connect/config.html
        //

        Optional.ofNullable(debugLevel).ifPresent(debug->System.setProperty(DEBUG_LEVEL,debug));
        Optional.ofNullable(authenticationType).ifPresent(type->System.setProperty(AUTHENTICATION,type));
        Optional.ofNullable(initSize).ifPresent(init->System.setProperty(CONNECTION_POOL_INIT_SIZE,init));
        Optional.ofNullable(prefSize).ifPresent(pref->System.setProperty(CONNECTION_POOL_MAX_SIZE,pref));
        Optional.ofNullable(poolConnectionTimeout).ifPresent(time->System.setProperty(CONNECTION_POOL_TIME_OUT,time));
//        System.setProperty(CONNECTION_POOL_INIT_SIZE,initSize);

    }

    @Bean
    @Scope("prototype")
    LdapContext ldapContext() throws NamingException {
        return new InitialLdapContext(env,null);
    }





    }
