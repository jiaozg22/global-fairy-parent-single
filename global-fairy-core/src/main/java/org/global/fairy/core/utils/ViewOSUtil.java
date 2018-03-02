package org.global.fairy.core.utils;

import java.util.Properties;

/**
 * 操作系统信息获取工具
 * 
 * @author jiao
 * 
 */
public class ViewOSUtil {
	/**
	 * 获取操作系统的名字
	 * 
	 * @return
	 */
	public static String viewOSName() {
		Properties prop = System.getProperties();
		String osName = prop.getProperty("os.name");
		System.out.println(osName);
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
