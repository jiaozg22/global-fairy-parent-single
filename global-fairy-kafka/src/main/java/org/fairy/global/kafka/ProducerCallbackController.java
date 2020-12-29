package org.fairy.global.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author jiao_zg22
 * @version 1.0
 * @description 带回调的生产者
 * 教程地址：https://www.bilibili.com/video/BV1a4411B7V9?p=28
 * @date 2020/12/29 19:30
 */
public class ProducerCallbackController {
    public static void main(String[] args) {
        //1.生产者配置信息
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"k8smaster:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //2.创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        //3.发送数据
//        for (int i = 0 ; i<10;i++){
//            producer.send(new ProducerRecord<String, String>("first", "k8s-record-" + i), (recordMetadata, e) -> {
//                if (e ==null){
//                    //offset 区内有序，全局不一定有序
//                    System.out.println("partition"+recordMetadata.partition()+"---"+"offset:"+recordMetadata.offset());
//                }else{
//                    System.out.println(e.getStackTrace());
//                }
//            });
//        }

        //3.指定分区发送数据
        for (int i = 0 ; i<10;i++){
            producer.send(new ProducerRecord<String, String>("first",0,"p=test", "p=k8s-record-" + i), (recordMetadata, e) -> {
                if (e ==null){
                    //offset 区内有序，全局不一定有序
                    System.out.println("partition:"+recordMetadata.partition()+"---"+"offset:"+recordMetadata.offset());
                }else{
                    System.out.println(e.getStackTrace());
                }
            });
        }

        //4.关闭资源
        producer.close();
    }

}
