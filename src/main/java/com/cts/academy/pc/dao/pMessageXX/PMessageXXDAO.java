package com.cts.academy.pc.dao.pMessageXX;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;

/**
 * Interface for  PMessageXXDAO
 * @author polun.piotr
 *
 */
public interface PMessageXXDAO {

    public String ROOT_DN = "dc=publishing,dc=cts-academy,dc=com";
    public String PARTNER_ID = "partnerID";
    public String BUCKET_TICK = "tick";
    public String MESSAGE_RDN = "messageID";
    public String PC_SEARCH_FILTER = "(objectClass=pMessage10)";
    /**
     * Build DN for a PMessageXX entry
     * @param messageId - message RND
     * @param bucketTick - bucket Tick
     * @param partnerID - partner's ID
     * @return instance of {@link LdapName}
     * @throws InvalidNameException
     */
    public LdapName buildDn(int messageId, int bucketTick, int partnerID) throws InvalidNameException;
    /**
     * Build DN for a PMessageXX entry
     * @param message - message instance
     * @return instance of {@link LdapName}
     * @throws InvalidNameException
     */
    public LdapName buildDn(PMessageXX message) throws NamingException;
    /**
     * Search for a PMessageXX entry
     * @param messageId - message RND
     * @param bucketTick - bucket Tick
     * @param partnerID - partner's ID
     * @return an instance of {@link PMessageXX}
     * @throws NamingException
     */
    public PMessageXX getPMessageXX(int messageId, int bucketTick, int partnerID) throws NamingException;
    /**
     * Add a new entry of PMessageXX
     * @param message - instance of {@link PMessageXX} to be added (created)
     * @throws NamingException
     */
    public void addPMessageXX(PMessageXX message) throws NamingException;
    /**
     * Delete an entry of PMessageXX
     * @param message - instance of {@link PMessageXX} to be removed
     * @throws NamingException
     */
    public void deletePMessageXX(PMessageXX message) throws NamingException;
    /**
     * Modify an entry of PMessageXX
     * @param message - instance of {@link PMessageXX} to be modified
     * @throws NamingException
     */
    public void modifyPMessageXX(PMessageXX message) throws NamingException;
}
