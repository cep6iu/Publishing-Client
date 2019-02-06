package com.cts.academy.pc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class with properties of application
 *
 * @author valeriu.vicol
 * <p>
 * //command to set the source
 *  java -jar -Dapp.home="/home/mkyon/test" example.jar
 */

@PropertySource(value = {"file:${path}"},ignoreResourceNotFound = true)
@Configuration
public class AppProperties {

    @Value("${ldap.password}")
    private String ldapPassword;

    @Value("${ldap.dn}")
    private String ldapDn;

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.connection.timeout}")
    private String ldapConnectionTimeout;

    @Value("${ldap.read.timeout}")
    private String ldapReadTimeout;

    @Value("${connection.pool.timeout}")
    private String connectionPoolTimeout;

    @Value("${connection.pool.max.size}")
    private String connectionPoolMaxSize;

    @Value("${connection.pool.initial.size}")
    private String connectionPoolInitialSize;

    @Value("${connection.pool.preferred.size}")
    private String connectionPoolPreferredSize;

    @Value("${connection.pool.idle.timeout}")
    private String connectionPoolIdleTimeout;

    @Value("${log.severity}")
    private String logSeverity;


    public String getLdapPassword() {
        return ldapPassword;
    }

    public String getLdapDn() {
        return ldapDn;

    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public String getLdapConnectionTimeout() {
        return ldapConnectionTimeout;
    }

    public String getLdapReadTimeout() {
        return ldapReadTimeout;
    }

    public String getConnectionPoolTimeout() {
        return connectionPoolTimeout;
    }

    public String getConnectionPoolMaxSize() {
        return connectionPoolMaxSize;
    }

    public String getConnectionPoolPreferredSize() {
        return connectionPoolPreferredSize;
    }

    public String getConnectionPoolIdleTimeout() {
        return connectionPoolIdleTimeout;
    }

    public String getLogSeverity() {
        return logSeverity;
    }


}
