package com.hope.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by lijin on  2022/3/25
 */
@Component
@Slf4j
public class KafkaConsumer {

    /**
     * 定时启动/停止监听器
     *
     * @param record
     */
    @KafkaListener(id = "timingConsumer", topics = "topic1", containerFactory = "delayContainerFactory")
    public void onMessage1(ConsumerRecord<?, ?> record) {
        log.info("onMessage1=================");
        System.out.println("消费成功：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    /**
     * @return void
     * @Title 消息转发
     * @Description 从topic1接收到的消息经过处理后转发到topic2
     * @Param [record]
     **/
    @KafkaListener(topics = {"topic1"})
    @SendTo("topic2")
    public void onMessage2(ConsumerRecord<?, ?> record) {
        log.info("onMessage2=================");
        System.out.println(record.value());
    }

    /**
     * 消息过滤监听
     *
     * @param record
     */
    @KafkaListener(topics = {"topic3"}, containerFactory = "filterContainerFactory")
    public void onMessage3(ConsumerRecord<?, ?> record) {
        log.info("onMessage3=================");
        System.out.println(record.value());
    }


    /**
     * @return void
     * @Title 指定topic、partition、offset消费
     * @Description 同时监听topic5和topic6，监听topic5的0号分区、topic6的 "0号和1号" 分区，指向1号分区的offset初始值为8
     * @Param [record]
     **/
    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "topic5", partitions = {"0"}),
            @TopicPartition(topic = "topic6", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
    })
    public void onMessage4(ConsumerRecord<?, ?> record) {
        log.info("onMessage4=================");
        System.out.println("topic:" + record.topic() + "|partition:" + record.partition() + "|offset:" + record.offset() + "|value:" + record.value());
    }


    /**
     * 批量消费
     *
     * @param records
     */
    @KafkaListener(topics = "topic7", containerFactory = "batchFactory")
    public void onMessage5(List<ConsumerRecord<?, ?>> records) {
        log.info("onMessage5=================");
        for (ConsumerRecord<?, ?> record : records) {
            // 打印消息的分区以及偏移量
            log.info("Kafka Consume partition:{}, offset:{}", record.partition(), record.offset());
            String value = (String) record.value();
            System.out.println("value = " + value);
        }
    }

    /**
     * @param record
     */
    @KafkaListener(topics = "topic4")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        log.info("onMessage6=================");
        System.out.println("消费成功：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }
}
