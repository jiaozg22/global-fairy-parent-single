//package org.global.fairy.redis;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: jiaozongguan
// * @Description:使用个性化配置时使用
// * 读取redis配置信息并装载  (參考：csdn：https://blog.csdn.net/u010199866/article/details/80705797)
// * @Date: Create in  20190703
// */
//@Component
//@ConfigurationProperties(prefix = "spring.redis.cache")
//public class RedisProperties {
//    private int expireSeconds;
//    private String clusterNodes;
//    private int commandTimeout;
//
//    public int getExpireSeconds() {
//        return expireSeconds;
//    }
//
//    public void setExpireSeconds(int expireSeconds) {
//        this.expireSeconds = expireSeconds;
//    }
//
//    public String getClusterNodes() {
//        return clusterNodes;
//    }
//
//    public void setClusterNodes(String clusterNodes) {
//        this.clusterNodes = clusterNodes;
//    }
//
//    public int getCommandTimeout() {
//        return commandTimeout;
//    }
//
//    public void setCommandTimeout(int commandTimeout) {
//        this.commandTimeout = commandTimeout;
//    }
//}
