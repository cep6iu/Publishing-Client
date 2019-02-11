package com.cts.academy.pc;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Interface for  manager class for database connections
 * @author alexandru.filipas
 *
 */


public class PublishingClient {
	private static Logger loger = LogManager.getLogger();
	public static void main(String[] args) throws NamingException {
		loger.info("abc");
	}
}
