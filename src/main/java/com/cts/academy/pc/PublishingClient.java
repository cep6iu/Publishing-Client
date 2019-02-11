package com.cts.academy.pc;

import javax.naming.NamingException;

import com.cts.academy.pc.configuration.AppConfig;
import com.cts.academy.pc.dao.pMessageXX.PMessageXXImpl;
import com.cts.academy.pc.dao.partnerQ.PartnerQImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Interface for  manager class for database connections
 * @author alexandru.filipas
 *
 */


public class PublishingClient {
	private static Logger loger = LogManager.getLogger();
	public static void main(String[] args) throws NamingException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PartnerQImpl dao = ctx.getBean(PartnerQImpl.class);
		loger.info(dao.getPartnerQ("10").toString());
	}
}
