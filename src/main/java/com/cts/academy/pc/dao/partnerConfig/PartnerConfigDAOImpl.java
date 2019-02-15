package com.cts.academy.pc.dao.partnerConfig;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.naming.InvalidNameException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import com.cts.academy.pc.configuration.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author serghei.sciurenco
 */

@Repository
public class PartnerConfigDAOImpl implements PartnerConfigDAO {

    @Autowired
    ConnectionManager conManager;

    @Override
    public LdapName buildDn(int partnerId) throws InvalidNameException {
        LdapName dn = new LdapName(PartnerConfigDAO.ROOT_DN);
        dn.add(new Rdn(PartnerConfig.PARTNER_ID, Integer.toString(partnerId)));
        return dn;
    }

    @Override
    public PartnerConfig getPartnerConfig(int partnerId) throws NamingException {
        LdapContext context = conManager.ldapContext();
        List<PartnerConfig> searchResults = new ArrayList<>();
        LdapName dn = buildDn(partnerId);

        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.OBJECT_SCOPE);
        NamingEnumeration<SearchResult> results = null;
        try {
            results = context.search(dn, PartnerConfigDAO.PC_SEARCH_FILTER, controls);
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                PartnerConfig pcof = new PartnerConfig();
                pcof.setDn(searchResult.getNameInNamespace());
                Attributes attributes = searchResult.getAttributes();

                pcof.setPartnerId(Integer.parseInt(attributes.get(PartnerConfig.PARTNER_ID).get().toString()));
                pcof.setPartnerName((String)attributes.get(PartnerConfig.PARTNER_NAME).get());
                pcof.setPartnerMaxNumRetry(Integer.parseInt(attributes.get(PartnerConfig.PARTNER_MAX_NUM_RETRY).get().toString()));
                pcof.setPartnerUrl((String)attributes.get(PartnerConfig.PARTNER_URL).get());
                searchResults.add(pcof);
            }
        } finally {
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
    public void addPartnerConfig(PartnerConfig partnerConfig) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(partnerConfig.getPartnerId());
        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(partnerConfig.getObjectClass());
            attrs.put(ocattr);
            attrs.put(PartnerConfig.PARTNER_ID, Integer.toString(partnerConfig.getPartnerId()));
            attrs.put(PartnerConfig.PARTNER_NAME, partnerConfig.getPartnerName());
            attrs.put(PartnerConfig.PARTNER_URL, partnerConfig.getPartnerUrl());
            attrs.put(PartnerConfig.PARTNER_MAX_NUM_RETRY, Integer.toString(partnerConfig.getPartnerMaxNumRetry()));
            context.bind(dn, null, attrs);

        } finally {
            context.close();
        }
    }

    @Override
    public void deletePartnerConfig(int partnerId) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(partnerId);
        try {
            context.unbind(dn);
        } finally {
            context.close();
        }
    }

    @Override
    public void modifyPartnerConfig(PartnerConfig partnerConfig) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(partnerConfig.getPartnerId());
        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(partnerConfig.getObjectClass());
            attrs.put(ocattr);
            attrs.put(PartnerConfig.PARTNER_URL, partnerConfig.getPartnerUrl());
            attrs.put(PartnerConfig.PARTNER_NAME, partnerConfig.getPartnerName());
            attrs.put(PartnerConfig.PARTNER_MAX_NUM_RETRY, Integer.toString(partnerConfig.getPartnerMaxNumRetry()));
            context.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE, attrs);
        } finally {
            context.close();
        }
    }
}
