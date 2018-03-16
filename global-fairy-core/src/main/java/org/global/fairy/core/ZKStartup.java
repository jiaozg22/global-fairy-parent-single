package org.global.fairy.core;

import org.global.fairy.core.utils.ViewOSUtil;

/**
 * 启动zookeeper
 * 
 * @author jiao
 * 
 */
public class ZKStartup {
	public static RunZKService zkService = null;

	public static void startup() {

		if (ViewOSUtil.isWindowOS()) {
			System.out.println("window操作系统");
			zkService = new RunWindowsZKService();
		}

		if (ViewOSUtil.isLinuxOS()) {
			System.out.println("linux操作系统");
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
			System.out.println("window操作系统");
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
