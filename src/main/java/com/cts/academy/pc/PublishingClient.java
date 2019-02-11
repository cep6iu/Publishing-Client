package com.cts.academy.pc;


import com.cts.academy.pc.configuration.AppProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.ldap.LdapContext;

public class PublishingClient {




	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppProperties.class);

		LdapContext c1 = ctx.getBean(LdapContext.class);
		LdapContext c2 = ctx.getBean(LdapContext.class);
		LdapContext c3 = ctx.getBean(LdapContext.class);
		LdapContext c4 = ctx.getBean(LdapContext.class);
	}
}
