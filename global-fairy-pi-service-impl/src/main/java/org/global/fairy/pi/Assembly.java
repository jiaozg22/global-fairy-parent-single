package org.global.fairy.pi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.core.ZKStartup;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 启动dubbo容器!
 * 
 */
public class Assembly {
	private static final Logger logger = LogManager.getLogger("Assembly.class");
	
	private static boolean startZKService() {
		boolean result = true;
		
		try {
			ZKStartup.startup();
			logger.info("自动启动zkservice成功！");
		} catch (Exception e) {
			logger.error("自动启动zkservice失败。请手动开启zookeeper的服务！");
			result = false;
		}
		return result;
	}

	private static void startSpringContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"global-fairy-dubbo-pi-provider.xml",
						"applicationContext-pi-service-impl.xml",
						"applicationContext-mybatis.xml" });

		context.start();

		logger.info("Press any key to exit.");

		try {
			int keyStr = System.in.read();
			logger.info("Press key"+keyStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void startDubbo() {
		startZKService();
		startSpringContext();
	}

	public static void stopDubbo() {
		ZKStartup.stopZKService();
		Thread.currentThread().interrupt();
	}

	public static void main(String[] args) throws Exception {
		startDubbo();
//		stopDubbo();
	}
}
