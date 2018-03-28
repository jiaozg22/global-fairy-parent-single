package org.global.fairy.core.utils;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 操作系统信息获取工具
 * 
 * @author jiao
 * 
 */
public class ViewOSUtil {
	private static final Logger logger = LogManager.getLogger("ViewOSUtil.class");
	
	/**
	 * 获取操作系统的名字
	 * 
	 * @return
	 */
	public static String viewOSName() {
		Properties prop = System.getProperties();
		String osName = prop.getProperty("os.name");
		logger.info(osName);
		return osName;
	}

	/**
	 * 判断是否是windows系统
	 * 
	 * @return
	 */
	public static boolean isWindowOS() {
		String os = viewOSName();
		
		return (os.startsWith("win") || os.startsWith("Win"));
	}

	/**
	 * 判断是否是linux系统
	 * 
	 * @return
	 */
	public static boolean isLinuxOS() {
		String os = viewOSName();
		
		return (os.startsWith("linux") || os.startsWith("Linux"));
	}

}
