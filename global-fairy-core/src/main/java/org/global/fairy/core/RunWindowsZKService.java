package org.global.fairy.core;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.core.utils.CallExe;
import org.global.fairy.core.utils.ViewOSUtil;

public class RunWindowsZKService implements RunZKService {
	private static final Logger logger = LogManager.getLogger("RunWindowsZKService.class");
	
	public Process ps = null;
	public String path;

	public void runZKService() {

		if (ViewOSUtil.isWindowOS()) {
			logger.info("ZOOKEEPER_HOME" + ZKEnvConfig.getZookeeperHomeEnv());
			path = "cmd.exe /c start " + ZKEnvConfig.getZookeeperHomeEnv() + "/bin/zkServer.cmd";
		} else {
			logger.info("操作系统不是windows");
		}

		logger.info("zookeeper启动路径：" + path);
		// String path =
		// "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		ps = CallExe.runCmd(path);
	}

	public void stopZKService() {
		int i = ps.exitValue();
		if (i == 0) {
			logger.info("ZKService stop 执行完成.");
		} else {
			logger.info("ZKService stop 执行失败.");
		}
		ps.destroy();
		ps = null;

		// 批处理执行完后，根据cmd.exe进程名称
		// kill掉cmd窗口
		killProcess();

	}

	public void killProcess() {
		Runtime rt = Runtime.getRuntime();
		try {
			if (ViewOSUtil.isWindowOS()) {
				rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
