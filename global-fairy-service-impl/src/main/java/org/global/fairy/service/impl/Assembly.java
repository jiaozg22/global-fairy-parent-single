package org.global.fairy.service.impl;

import java.io.IOException;

import org.global.fairy.core.RunWindowsZKService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动dubbo容器!
 * 
 */
public class Assembly {
	private static boolean startZKService() {
		boolean result = true;
		try {
			RunWindowsZKService.runZKService();
		} catch (Exception e) {
			System.out.println("自动启动zkservice失败。请手动开启zookeeper的服务！");
			result = false;
		}
		return result;
	}

	private static void startSpringContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "global-fairy-dubbo-provider.xml",
						"applicationContext-service-impl.xml",
						"applicationContext-mybatis.xml" });

		context.start();

		System.out.println("Press any key to exit.");

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void startDubbo() {
		startZKService();
		startSpringContext();
	}

	public static void stopDubbo() {
		RunWindowsZKService.stopZKService();
	}

	public static void main(String[] args) throws Exception {
		startDubbo();
		stopDubbo();
	}
}
