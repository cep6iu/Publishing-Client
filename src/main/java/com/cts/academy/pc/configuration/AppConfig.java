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
public class AppConfig {

    /**
     * static properties witch will set as system variables
     */
    public static final String AUTHENTICATION = "con.sun.jndi.ldap.connect.pool.authentication";
    public static final String DEBUG_LEVEL = "com.sun.jndi.ldap.connect.pool.debug";
    public static final String CONNECTION_POOL_INIT_SIZE = "com.sun.jndi.ldap.connect.pool.initsize";
    public static final String CONNECTION_POOL_MAX_SIZE = "com.sun.jndi.ldap.connect.pool.maxsize";
    public static final String CONNECTION_POOL_PREF_SIZE = "com.sun.jndi.ldap.connect.pool.prefsize";
    public static final String CONNECTION_POOL_TIME_OUT = "com.sun.jndi.ldap.connect.pool.timeout";

}