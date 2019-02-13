package com.cts.academy.pc.configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class witch represent context of application
 *
 *  @author valeriu.vicol
 *
 * //command to set the source
 *  -Dconfig.path=/opt/pc/config
 *
 *
 */

@PropertySource(value = {"file:${config.path}/application.properties"},ignoreResourceNotFound = true)
@Configuration
@ComponentScan(basePackages = "com.cts")
public class AppConfig {


    public static final String CONNECTION_POOL_INIT_SIZE = "com.sun.jndi.ldap.connect.pool.initsize";
    public static final String CONNECTION_POOL_MAX_SIZE = "com.sun.jndi.ldap.connect.pool.maxsize";
    public static final String CONNECTION_POOL_PREF_SIZE = "com.sun.jndi.ldap.connect.pool.prefsize";
    public static final String CONNECTION_POOL_TIME_OUT = "com.sun.jndi.ldap.connect.pool.timeout";
    public static final String LDAP_CONNECTION_TIME_OUT="com.sun.jndi.ldap.connect.timeout";
    public static final String LDAP_READ_TIME_OUT="com.sun.jndi.ldap.read.timeout";
    public static final String POOLING="com.sun.jndi.ldap.connect.pool";



}