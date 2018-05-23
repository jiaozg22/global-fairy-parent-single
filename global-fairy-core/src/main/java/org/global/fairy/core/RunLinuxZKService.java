package org.global.fairy.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.core.utils.CallExe;
import org.global.fairy.core.utils.ViewOSUtil;

public class RunLinuxZKService implements RunZKService {
	private static final Logger logger = LogManager.getLogger("RunLinuxZKService.class");
	
	public Process ps = null;
	public String cmd;

	@Override
	public void runZKService() {
		if (ViewOSUtil.isLinuxOS()) {
			cmd = ZKEnvConfig.getZookeeperHomeEnv() + "/bin/zkServer.sh start";
		}else{
			logger.info("不是Linux系统");
		}
		System.out.println("zookeeper启动路径：" + cmd);
		// String path =
		// "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		ps = CallExe.runCmd(cmd);
		if(ps!=null){
			try {
				ps.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			logger.info("启动命令成功");
		}
	}

	@Override
	public void stopZKService() {
		if (ViewOSUtil.isLinuxOS()) {
			cmd = ZKEnvConfig.getZookeeperHomeEnv() + "/bin/zkServer.sh stop";
		}else{
			logger.info("不是Linux系统");
		}
		System.out.println("zookeeper关闭命令：" + cmd);
		// String path =
		// "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		ps = CallExe.runCmd(cmd);
		
	}

	@Override
	public void killProcess() {
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
		//killProcess();
		
	}

}
