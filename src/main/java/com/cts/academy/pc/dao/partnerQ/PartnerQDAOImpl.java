package com.cts.academy.pc.dao.partnerQ;


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
 * Implementation for  PartnerQDAO Interface
 * @author polun.piotr
 *
 */
@Repository
public class PartnerQDAOImpl implements PartnerQDAO {

    @Autowired
    ConnectionManager conManager;

    @Override
    public LdapName buildDn(int partnerQID) throws InvalidNameException {
        LdapName dn = new LdapName(PartnerQDAO.ROOT_DN);
        dn.add(new Rdn(PartnerQ.PARTNER_ID, Integer.toString(partnerQID)));
        return dn;
    }

    @Override
    public PartnerQ getPartnerQ(int partnerQId) throws NamingException {
        LdapContext context = conManager.ldapContext();
        List<PartnerQ> searchResults = new ArrayList<>();
        // build orgpsn DN
        LdapName dn = buildDn(partnerQId);
        // build SearchControls instance
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.OBJECT_SCOPE);
        NamingEnumeration<SearchResult> results = null;
        try {
            results = context.search(dn, PartnerQDAO.PC_SEARCH_FILTER, controls);
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                PartnerQ msg = new PartnerQ();
                msg.setDn(searchResult.getNameInNamespace());
                Attributes attributes = searchResult.getAttributes();

                msg.setPartnerId(Integer.parseInt(attributes.get(PartnerQ.PARTNER_ID).get().toString()));
                msg.setStartTick(Integer.parseInt(attributes.get(PartnerQ.PARTNER_START_TICK).get().toString()));
                msg.setEndTick(Integer.parseInt(attributes.get(PartnerQ.PARTNER_END_TICK).get().toString()));
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

    @Override
    public void addPartnerQ(PartnerQ partnerQ) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(partnerQ.getPartnerId());
        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(partnerQ.getObjectClass());
            attrs.put(ocattr);
            attrs.put(PartnerQ.PARTNER_ID, Integer.toString(partnerQ.getPartnerId()));
            attrs.put(PartnerQ.PARTNER_START_TICK, Integer.toString(partnerQ.getStartTick()));
            attrs.put(PartnerQ.PARTNER_END_TICK, Integer.toString(partnerQ.getEndTick()));
            context.bind(dn, null, attrs);
        } finally {
            context.close();
        }

    }

    @Override
    public void deletePartnerQ(PartnerQ partnerQ) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(partnerQ.getPartnerId());
        try {
            context.unbind(dn);
        }
        catch (javax.naming.NameNotFoundException e) {
        }finally {
            context.close();
        }
    }

    @Override
    public void modifyPartnerQ(PartnerQ partnerQ) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(partnerQ.getPartnerId());
        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(partnerQ.getObjectClass());
            attrs.put(ocattr);
            attrs.put(PartnerQ.PARTNER_START_TICK, Integer.toString(partnerQ.getStartTick()));
            attrs.put(PartnerQ.PARTNER_END_TICK, Integer.toString(partnerQ.getEndTick()));
            context.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE, attrs);
        }
        finally {
            context.close();
        }

    }
}
