package org.global.fairy.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.core.utils.ViewOSUtil;

/**
 * 启动zookeeper
 * 
 * @author jiao
 * 
 */
public class ZKStartup {
	private static final Logger logger = LogManager.getLogger("ZKStartup.class");
	
	public static RunZKService zkService = null;

	public static void startup() {

		if (ViewOSUtil.isWindowOS()) {
			logger.info("window操作系统");
			zkService = new RunWindowsZKService();
		}

		if (ViewOSUtil.isLinuxOS()) {
			logger.info("linux操作系统");
			zkService = new RunLinuxZKService();
		}

		zkService.runZKService();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		zkService.stopZKService();
	}

	public static void stop() {
		if (ViewOSUtil.isWindowOS()) {
			logger.info("window操作系统");
			zkService = new RunWindowsZKService();
		}

		if (ViewOSUtil.isLinuxOS()) {
			zkService = new RunLinuxZKService();
		}

//		zkService.runZKService();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		zkService.stopZKService();
	}

	public static void main(String[] args) throws InterruptedException {
		startup();
	}

	/**
	 * 主动关闭zk
	 */
	public static void stopZKService() {
		zkService.stopZKService();
	}

}
