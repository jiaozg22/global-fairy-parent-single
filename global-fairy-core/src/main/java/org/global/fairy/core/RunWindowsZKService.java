package org.global.fairy.core;

import java.io.IOException;

import org.global.fairy.core.utils.CallExe;
import org.global.fairy.core.utils.ViewOSUtil;

public class RunWindowsZKService implements RunZKService {
	public Process ps = null;
	public String path;

	public void runZKService() {

		if (ViewOSUtil.isWindowOS()) {
			System.out.println("ZOOKEEPER_HOME" + ZKEnvConfig.getZookeeperHomeEnv());
			path = "cmd.exe /c start " + ZKEnvConfig.getZookeeperHomeEnv() + "/bin/zkServer.cmd";
		} else {
			System.out.println("操作系统不是windows");
		}

		System.out.println("zookeeper启动路径：" + path);
		// String path =
		// "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		ps = CallExe.runCmd(path);
	}

	public void stopZKService() {
		int i = ps.exitValue();
		if (i == 0) {
			System.out.println("ZKService stop 执行完成.");
		} else {
			System.out.println("ZKService stop 执行失败.");
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
		}
	}

}
