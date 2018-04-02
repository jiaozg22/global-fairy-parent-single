package org.global.fairy.core;

public class ZKEnvConfig {
	private String ZOOKEEPER_HOME_VALUE = "ZOOKEEPER_HOME";
	private String ZOOKEEPER_HOME = EnvGet.getEnvByKey(ZOOKEEPER_HOME_VALUE);

	public static String getZookeeperHomeEnv(){
		ZKEnvConfig ZKEnvConfig = new ZKEnvConfig();
		return  ZKEnvConfig.ZOOKEEPER_HOME;
	}
}
