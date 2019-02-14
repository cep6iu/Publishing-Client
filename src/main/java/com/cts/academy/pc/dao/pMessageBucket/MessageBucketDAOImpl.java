package com.cts.academy.pc.dao.pMessageBucket;

import com.cts.academy.pc.configuration.ConnectionManager;
import com.cts.academy.pc.dao.pMessageXX.PMessageXX;
import com.cts.academy.pc.dao.pMessageXX.PMessageXXDAO;
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

@Repository
public class MessageBucketDAOImpl implements MessageBucketDAO {
    @Autowired
    ConnectionManager conManager;

    @Override
    public LdapName buildDn(int tick, int partnerId) throws InvalidNameException {
        LdapName dn = new LdapName(MessageBucketDAO.ROOT_DN);
        dn.add(new Rdn(MessageBucket.PARTNER_ID, Integer.toString(partnerId)));
        dn.add(new Rdn(MessageBucket.TICK, Integer.toString(tick)));
        return dn;
    }

    @Override
    public LdapName buildDn(MessageBucket messageBucket) throws InvalidNameException {
            return this.buildDn( messageBucket.getTick(), messageBucket.getPartnerId());
    }

    @Override
    public void addMsgBucketDao(MessageBucket messageBucket) throws NamingException {
        LdapContext context = conManager.ldapContext();
        LdapName dn = this.buildDn(messageBucket);
        try {
            Attributes attrs = new BasicAttributes();
            BasicAttribute ocattr = new BasicAttribute("objectClass");
            ocattr.add(messageBucket.getObjectClass());
            attrs.put(ocattr);
            attrs.put(MessageBucket.TICK, Integer.toString(messageBucket.getTick()));
            context.bind(dn, null, attrs);
        } finally {
            context.close();
        }
    }

    @Override
    public MessageBucket getMsgBucketDao(int partnerId, int tick) throws NamingException {
            LdapContext context = conManager.ldapContext();
            List<MessageBucket> searchResults = new ArrayList<>();
            LdapName dn = buildDn(partnerId, tick);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.OBJECT_SCOPE);
            NamingEnumeration<SearchResult> results = null;
        try {
            results = context.search(dn, MessageBucketDAO.PC_SEARCH_FILTER, controls);
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                MessageBucket msg = new MessageBucket();
                msg.setDn(searchResult.getNameInNamespace());
                Attributes attributes = searchResult.getAttributes();
                msg.setTick(Integer.parseInt(attributes.get(MessageBucket.TICK).get().toString()));
                searchResults.add(msg);
            }
        }  finally {
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
    public void deleteMsgBucketDao(MessageBucket messageBucket) throws NamingException {
            LdapContext context = conManager.ldapContext();
            LdapName dn = this.buildDn(messageBucket);
            try {
                context.unbind(dn);
            } finally {
                context.close();
            }
    }
}
