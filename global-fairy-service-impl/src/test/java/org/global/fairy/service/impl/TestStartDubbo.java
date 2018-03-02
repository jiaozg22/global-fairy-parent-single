package org.global.fairy.service.impl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStartDubbo {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");
			context.start();
		} catch (Exception e) {
//			log.error("== DubboProvider context start error:",e);
		}
		synchronized (TestStartDubbo.class) {
			while (true) {
				try {
					TestStartDubbo.class.wait();
				} catch (InterruptedException e) {
//					log.error("== synchronized error:",e);
				}
			}
		}
	}
}
