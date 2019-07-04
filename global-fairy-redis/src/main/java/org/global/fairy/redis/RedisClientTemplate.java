//package org.global.fairy.redis;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * 整理：jiaozongguan
// * @Author: nanjunyu
// * @Description:使用个性化配置时使用
//	 * --------------------- 
//	作者：2018Frank 
//	来源：CSDN 
//	原文：https://blog.csdn.net/u010199866/article/details/80705797 
//	版权声明：本文为博主原创文章，转载请附上博文链接！
// * @Date: Create in  2018/6/14 16:34
// */
//@Service
//public class RedisClientTemplate {
//    private static final Logger log=LoggerFactory.getLogger(RedisClientTemplate.class);
//
//    @Autowired
//    private JedisClusterConfig jedisClusterConfig;
//
//    public boolean setToRedis(String key,Object value){
//        try {
//        String str=jedisClusterConfig.getJedisCluster().set(key, String.valueOf(value));
//        if("OK".equals(str))
//            return true;
//        }catch (Exception ex){
//            log.error("setToRedis:{Key:"+key+",value"+value+"}",ex);
//        }
//        return false;
//    }
//
//    public Object getRedis(String key){
//        String str=null;
//        try {
//             str=jedisClusterConfig.getJedisCluster().get(key);
//        }catch (Exception ex){
//            log.error("getRedis:{Key:"+key+"}",ex);
//        }
//        return str;
//    }
//    
//}
