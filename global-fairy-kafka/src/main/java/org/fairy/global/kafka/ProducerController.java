package org.fairy.global.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author jiao_zg22
 * @version 1.0
 * @description 接口
 * @date 2020/12/29 17:35
 */
public class ProducerController {

    public static void main(String[] args) {
        //1.配置文件
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,"");
        properties.put("bootstrap.servers","k8smaster:9092");
        properties.put("acks","all");
        properties.put("retries ", "3");//重试次数
        properties.put("batch.size","16384");//批次大小
        properties.put("linger.ms","1");//   等待时间 ms
        properties.put("buffer.memory","33554422");//   recordAccumulator:缓冲区大小
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        //2.创建生产者
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        for (int i =0 ;i <10;i++){
            producer.send(new ProducerRecord<String, String>("first","global-fairy-kafka-"+i));
        }

        //3.关闭连接
        producer.close();

    }
}
