package com.cts.academy.pc.dao.pMessageBucket;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;

/**
 *
 * @author serghei.sciurenco
 */
public interface MessageBucketDAO {
    public String ROOT_DN = "dc=publishing,dc=cts-academy,dc=com";
    public String PC_SEARCH_FILTER = "(objectClass=pMsgBucket)";
    /**
     * Build DN  for MessageBucket
     * @param partnerId
     * @param tick
     * @return instance of {@link LdapName}
     * @throws InvalidNameException
     */
    public LdapName buildDn(int tick, int partnerId) throws InvalidNameException;
/**
 * Build DN for MessageBucket
 * @param messageBucket
 * @return instance of {@link LdapName}
 * @throws NamingException
 *
 */
    public LdapName buildDn(MessageBucket messageBucket) throws NamingException;

    /**
     * Search an entry
     * @param partnerId
     * @return instance of {@link LdapName}
     * @throws NamingException
     */

    public MessageBucket getMsgBucketDAO(int partnerId, int tick) throws NamingException;

    /**
     * Add a new entry
     * @param messageBucket
     * @return instance of {@link LdapName}
     * @throws NamingException
     */
    public void addMsgBucketDAO(MessageBucket messageBucket) throws NamingException;

    /**
     * Delete an entry
     * @param messageBucket
     * @return instance of {@link LdapName}
     * @throws NamingException
     */
    public void deleteMsgBucketDAO(MessageBucket messageBucket) throws NamingException;
}
