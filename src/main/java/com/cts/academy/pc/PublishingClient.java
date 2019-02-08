package com.cts.academy.pc;

import com.cts.academy.pc.dao.pMessageXX.PMessageXX;
import com.cts.academy.pc.dao.pMessageXX.PMessageXXDAO;
import com.cts.academy.pc.dao.pMessageXX.PMessageXXImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.NamingException;

/**
 * Interface for  manager class for database connections
 * @author alexandru.filipas
 *
 */

public class PublishingClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	}

	void Testing(){
		PMessageXXDAO dao = new PMessageXXImpl();

		try {
			System.out.println(dao.getPMessageXX("132", "0", "10"));
/*
			PMessageXX addEntity = new PMessageXX();
			addEntity.setMessageID("132");
			addEntity.setBucketTick("0");
			addEntity.setPartnerID(10);
			addEntity.setMessage("Polun Pete Test");
			dao.addPMessageXX(addEntity);*/

			// modify an entry of subscriber AUC

			/*addEntity.setMessageID("1");
			addEntity.setBucketTick("0");
			addEntity.setPartnerID("20");
			addEntity.setMessage("Polun Pete Test == 2");
            dao.modifyPMessageXX(addEntity);
            System.out.println(dao.getPMessageXX("132", "0", "10"));*/

			//dao.deletePMessageXX(addEntity);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
