package org.global.fairy.core;

import java.io.IOException;

import org.global.fairy.core.utils.CallExe;

public class RunWindowsZKService {
	static Process ps = null;
	
	public static void runZKService() {
		String ZOOKEEPER_HOME_VALUE = "ZOOKEEPER_HOME";
		String ZOOKEEPER_HOME = EnvGet.getEnvByKey(ZOOKEEPER_HOME_VALUE);
		String path = "cmd.exe /c start " + ZOOKEEPER_HOME
				+ "/bin/zkServer.cmd";
		// String path =
		// "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		ps = CallExe.runCmd(path);
	}

	public static void stopZKService() {
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

	public static void killProcess() {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		runZKService();
		Thread.sleep(5000);
		stopZKService();
	}

}
