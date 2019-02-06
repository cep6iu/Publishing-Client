package com.cts.academy.pc;


import com.cts.academy.pc.configuration.AppProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PublishingClient {
	
	public static void main(String[] args) {
		//TODO remove
		System.setProperty("path", "src/main/resources/application.properties");

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppProperties.class);
		ctx.refresh();
	}
}
