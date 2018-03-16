package org.global.fairy.core.utils;

import java.io.IOException;


public class CallExe {
	
	public static Process runCmd(String cmd){
		//path = "cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd";
		try {
			return Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("运行"+cmd+"命令失败");
			return null;
		}
    	
		
	}

    /**
     * @param args
     */
    public static void main(String[] args) {
    	try {
			Runtime.getRuntime().exec("cmd.exe /c start d:/\"apache-zookeeper-3.4.8\"/bin\"/zkServer.cmd");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
//    	
//        Runtime rt = Runtime.getRuntime();
//        Process p = null;
//        String fileLac = "";
//        try {
//            fileLac = "cmd.exe";//zookeeper路径
//            //D:/apache-zookeeper-3.4.8/bin/zkServer.cmd
//            p = rt.exec(fileLac);
//            System.out.println("启动zookeeper：zkServer.cmd");
//        } catch (Exception e) {
//            System.out.println("open failure");
//        }
    }

}


