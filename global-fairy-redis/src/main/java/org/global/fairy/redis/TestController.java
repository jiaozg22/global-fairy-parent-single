//package org.global.fairy.redis;
//
//import com.springboot.buying.snapup.utils.redis.RedisClientTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
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
// * @Date: Create in  2018/6/14 17:31
// */
//@RestController
//@RequestMapping(value = "/test")
//public class TestController {
//
//    @Autowired
//    RedisClientTemplate redisClientTemplate;
//
//    @GetMapping(value = "/testSet")
//    public Object testSet(){
//        redisClientTemplate.setToRedis("Frank","Frank测试redis");
//        System.out.println(redisClientTemplate.getRedis("Frank"));
//        return null;
//    }
//
//}
