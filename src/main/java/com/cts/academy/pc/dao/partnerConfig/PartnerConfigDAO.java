/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.academy.pc.dao.partnerConfig;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;

/**
 * @author serghei.sciurenco
 */
public interface PartnerConfigDAO {

    public String ROOT_DN = "dc=partner,dc=cts-academy,dc=com";
    public String PC_SEARCH_FILTER = "(objectClass=partnerConfig)";

    /**
     * Build DN for PartnerConfig
     *
     * @param partnerId
     * @return instance of {@link LdapName}
     * @throws InvalidNameException
     */
    public LdapName buildDn(int partnerId) throws InvalidNameException;

    /**
     * Search an entry
     *
     * @param partnerId
     * @return found instance of {@link LdapName}
     * @throws NamingException
     */

    public PartnerConfig getPartnerConfig(int partnerId) throws NamingException;

    /**
     * Add a new entry
     *
     * @param partnerConfig
     * @return added instance of {@link LdapName}
     * @throws NamingException
     */
    public void addPartnerConfig(PartnerConfig partnerConfig) throws NamingException;

    /**
     * Delete an entry
     *
     * @param partnerId
     * @return deleted instance of {@link LdapName}
     * @throws NamingException
     */
    public void deletePartnerConfig(int partnerId) throws NamingException;

    /**
     * Modify an entry
     *
     * @param partnerConfig
     * @return modified instance of {@link LdapName}
     * @throws NamingException
     */
    public void modifyPartnerConfig(PartnerConfig partnerConfig) throws NamingException;
}
