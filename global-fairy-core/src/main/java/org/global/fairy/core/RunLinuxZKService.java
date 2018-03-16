package org.global.fairy.core;

import org.global.fairy.core.utils.CallExe;
import org.global.fairy.core.utils.ViewOSUtil;

public class RunLinuxZKService implements RunZKService {
	public Process ps = null;
	public String path;

	@Override
	public void runZKService() {
		if (ViewOSUtil.isLinuxOS()) {
			path = "." + ZKEnvConfig.getZookeeperHomeEnv() + "/bin/zkServer.sh";
		}else{
			System.out.println("不是Linux系统");
		}
		System.out.println("zookeeper启动路径：" + path);
		// String path =
		// "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		ps = CallExe.runCmd(path);
	}

	@Override
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

	@Override
	public void killProcess() {
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

}
