//package org.global.fairy.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 整理：jiaozongguan
// * @Author: nanjunyu
// * @Description:使用个性化配置时使用
// * --------------------- 
//	作者：2018Frank 
//	来源：CSDN 
//	原文：https://blog.csdn.net/u010199866/article/details/80705797 
//	版权声明：本文为博主原创文章，转载请附上博文链接！
// * @Date: Create in  2018/6/14 16:28
// */
//@Configuration
//public class JedisClusterConfig {
//    @Autowired
//    private RedisProperties redisProperties;
//
//    public JedisCluster getJedisCluster(){
//        String [] serverArray=redisProperties.getClusterNodes().split(",");
//        Set<HostAndPort> nodes=new HashSet<>();
//
//        for (String ipPort:serverArray){
//            String [] ipPortPair=ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
//
//        }
//        return  new JedisCluster(nodes,redisProperties.getCommandTimeout());
//    }
//
//}
