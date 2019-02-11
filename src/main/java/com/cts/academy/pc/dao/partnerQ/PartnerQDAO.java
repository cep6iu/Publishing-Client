package com.cts.academy.pc.dao.partnerQ;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;

/**
 * Interface for  PartnerQ Object Class
 * @author polun.piotr
 *
 */
public interface PartnerQDAO {

    public String ROOT_DN = "dc=publishing,dc=cts-academy,dc=com";
    public String PARTNER_ID = "partnerID";
    public String START_TICK = "startTick";
    public String END_TICK = "endTick";
    public String PC_SEARCH_FILTER = "(objectClass=partnerQ)";
    /**
     * Build DN for a PartnerQ entry
     * @param partnerQID - partnerQ RND
     * @return instance of {@link LdapName}
     * @throws InvalidNameException
     */
    public LdapName buildDn(String partnerQID) throws InvalidNameException;
    /**
     * Build DN for a PartnerQ entry
     * @param partnerQId - partner instance
     * @return instance of {@link LdapName}
     * @throws InvalidNameException
     */
    public PartnerQ getPartnerQ(String partnerQId) throws NamingException;
    /**
     * Add a new entry of PartnerQ
     * @param partnerQ - instance of {@link PartnerQ} to be added (created)
     * @throws NamingException
     */
    public void addPartnerQ(PartnerQ partnerQ) throws NamingException;
    /**
     * Delete an entry of PartnerQ
     * @param partnerQ - instance of {@link PartnerQ} to be removed
     * @throws NamingException
     */
    public void deletePartnerQ(PartnerQ partnerQ) throws NamingException;
    /**
     * Modify an entry of PartnerQ
     * @param partnerQ - instance of {@link PartnerQ} to be modified
     * @throws NamingException
     */
    public void modifyPartnerQ(PartnerQ partnerQ) throws NamingException;
}
