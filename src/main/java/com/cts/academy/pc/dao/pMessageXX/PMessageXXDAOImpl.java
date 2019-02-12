package com.cts.academy.pc.dao.pMessageXX;



import com.cts.academy.pc.configuration.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.InvalidNameException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for  PMessageXXDAO Interface
 * @author polun.piotr
 *
 */
@Repository
public class PMessageXXDAOImpl implements PMessageXXDAO {

    @Autowired
    ConnectionManager conManager;

    @Override
    public LdapName buildDn(int messageId, int bucketTick, int partnerID) throws InvalidNameException {
        LdapName dn = new LdapName(PMessageXXDAO.ROOT_DN);
        dn.add(new Rdn(PMessageXXDAO.PARTNER_ID, Integer.toString(partnerID)));
        dn.add(new Rdn(PMessageXXDAO.BUCKET_TICK, Integer.toString(bucketTick)));
        dn.add(new Rdn(PMessageXXDAO.MESSAGE_RDN, Integer.toString(messageId)));
        return dn;
    }

    @Override
    public LdapName buildDn(PMessageXX message) throws InvalidNameException {
        return this.buildDn(message.getMessageID(), message.getBucketTick(), message.getPartnerID());
    }

    @Override
    public PMessageXX getPMessageXX(int messageId, int bucketTick, int partnerID) throws NamingException {
        LdapContext context = conManager.ldapContext();
        List<PMessageXX> searchResults = new ArrayList<>();
        // build orgpsn DN
        LdapName dn = buildDn(messageId, bucketTick, partnerID);
        // build SearchControls instance
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.OBJECT_SCOPE);
        NamingEnumeration<SearchResult> results = null;

        try {
            results = context.search(dn, PMessageXXDAO.PC_SEARCH_FILTER, controls);
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                PMessageXX msg = new PMessageXX();
                msg.setDn(searchResult.getNameInNamespace());
                Attributes attributes = searchResult.getAttributes();

                msg.setMessageID(Integer.parseInt(attributes.get(PMessageXX.MESSAGE_ID).get().toString()));
                msg.setMessageReEnq(Integer.parseInt(attributes.get(PMessageXX.MESSAGE_RE_ENQ).get().toString()));
                msg.setMessage(attributes.get(PMessageXX.MESSAGE).get().toString());
                msg.setCustomerIdentifier(attributes.get(PMessageXX.MESSAGE_CUSTOMER_ID).get().toString());
                searchResults.add(msg);
            }
        }
        finally {
            if (results != null) {
                results.close();
            }
            context.close();
        }
        if (!searchResults.isEmpty()) {
            return searchResults.get(0);
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void addPMessageXX(PMessageXX message) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(message);
        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(message.getObjectClass());
            attrs.put(ocattr);
            attrs.put(PMessageXX.MESSAGE_ID, Integer.toString(message.getMessageID()));
            attrs.put(PMessageXX.MESSAGE_RE_ENQ, Integer.toString(message.getMessageReEnq()));
            attrs.put(PMessageXX.MESSAGE_CUSTOMER_ID, message.getCustomerIdentifier());
            attrs.put(PMessageXX.MESSAGE, message.getMessage());
            context.bind(dn, null, attrs);
        } finally {
            context.close();
        }
    }

    @Override
    public void deletePMessageXX(PMessageXX message) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(message);

        try {
            context.unbind(dn);
        }
        catch (javax.naming.NameNotFoundException e) {
        }finally {
            context.close();
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void modifyPMessageXX(PMessageXX message) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(message);

        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(message.getObjectClass());
            attrs.put(ocattr);
            attrs.put(PMessageXX.MESSAGE_ID, Integer.toString(message.getMessageID()));
            attrs.put(PMessageXX.MESSAGE_RE_ENQ, Integer.toString(message.getMessageReEnq()));
            attrs.put(PMessageXX.MESSAGE_CUSTOMER_ID, message.getCustomerIdentifier());
            attrs.put(PMessageXX.MESSAGE, message.getMessage());
            context.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE, attrs);
        }
        finally {
            context.close();
        }
    }
}
