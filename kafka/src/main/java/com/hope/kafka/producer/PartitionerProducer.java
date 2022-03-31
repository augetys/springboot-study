package com.hope.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by lijin on  2022/3/30
 */
public class PartitionerProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "110.42.244.186:9092");
        // 设置分区器
        props.put("partitioner.class", "com.hope.kafka.partition.CustomizePartitioner");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("topic1","李锦");
        RecordMetadata metadata = producer.send(record).get();
        String result = "value [" + record.value() + "] has been sent to partition " + metadata.partition();
        System.out.println(result);
        producer.close();
    }
}
