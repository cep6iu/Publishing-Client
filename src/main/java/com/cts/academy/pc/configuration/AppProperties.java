package com.cts.academy.pc.configuration;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

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
@ComponentScan
public class AppProperties {

    /**
     *      static properties witch will set as system variables
     */
    public static final String AUTHENTICATION="con.sun.jndi.ldap.connect.pool.authentication";
    public static final String DEBUG_LEVEL="com.sun.jndi.ldap.connect.pool.debug";
    public static final String CONNECTION_POOL_INIT_SIZE="com.sun.jndi.ldap.connect.pool.initsize";
    public static final String CONNECTION_POOL_MAX_SIZE="com.sun.jndi.ldap.connect.pool.maxsize";
    public static final String CONNECTION_POOL_PREF_SIZE="com.sun.jndi.ldap.connect.pool.prefsize";
    public static final String CONNECTION_POOL_TIME_OUT="com.sun.jndi.ldap.connect.pool.timeout";


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
     *
     * getters for properties
     */



    public String getLdapPassword() {
        return ldapPassword;
    }

    public String getLdapDn() {
        return ldapDn;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public String getDebugLevel() {
        return debugLevel;
    }

    public String getInitSize() {
        return initSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public String getPrefSize() {
        return prefSize;
    }

    public String getPoolConnectionTimeout() {
        return poolConnectionTimeout;
    }

    public String getLdapConnectionTimeout() {
        return ldapConnectionTimeout;
    }

    public String getLdapReadTimeout() {
        return ldapReadTimeout;
    }





    }
