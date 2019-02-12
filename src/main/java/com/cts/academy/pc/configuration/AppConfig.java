package com.cts.academy.pc.configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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

@PropertySource(value = {"file:${config.path}/application.properties"},ignoreResourceNotFound = true)
@Configuration
@ComponentScan(basePackages = "com.cts")
public class AppConfig {

    /**
     * static properties witch will set as system variables
     */
    public static final String AUTHENTICATION = "con.sun.jndi.ldap.connect.pool.authentication";
    public static final String CONNECTION_POOL_INIT_SIZE = "com.sun.jndi.ldap.connect.pool.initsize";
    public static final String CONNECTION_POOL_MAX_SIZE = "com.sun.jndi.ldap.connect.pool.maxsize";
    public static final String CONNECTION_POOL_PREF_SIZE = "com.sun.jndi.ldap.connect.pool.prefsize";
    public static final String CONNECTION_POOL_TIME_OUT = "com.sun.jndi.ldap.connect.pool.timeout";
    public static final String LDAP_CONNECTION_TIME_OUT="com.sun.jndi.ldap.connect.timeout";
    public static final String LDAP_READ_TIME_OUT="com.sun.jndi.ldap.read.timeout";
    public static final String POOLING="com.sun.jndi.ldap.connect.pool";



}