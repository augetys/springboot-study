package com.hope.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Create by lijin on 2022/2/24 19:37
 */
@SpringBootTest
@Slf4j
public class KafkaApplicationTests {

    @Test
    public void contextLoads() {

    }

    public AdminClient getAdmin() {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "110.42.244.186:9092");
        return AdminClient.create(prop);
    }


    /**
     * 创建主题
     */
    @Test
    public void testCreateTopic(){
        ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
        // 创建主题  参数：主题名称、分区数、副本数
        NewTopic topic1 = new NewTopic("topic1", 1, (short)1);
        NewTopic topic2 = new NewTopic("topic2", 1, (short)1);
        NewTopic topic3 = new NewTopic("topic3", 1, (short)1);
        NewTopic topic4 = new NewTopic("topic4", 1, (short)1);
        NewTopic topic5 = new NewTopic("topic5", 1, (short)1);
        NewTopic topic6 = new NewTopic("topic6", 1, (short)1);
        NewTopic topic7 = new NewTopic("topic7", 1, (short)1);
        topics.add(topic1);
        topics.add(topic2);
        topics.add(topic3);
        topics.add(topic4);
        topics.add(topic5);
        topics.add(topic6);
        topics.add(topic7);
        CreateTopicsResult result = getAdmin().createTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除主题
     */
    @Test
    public void testDeleteTopic(){
        ArrayList<String> topics = new ArrayList<>();
        topics.add("topic1");
        topics.add("topic2");
        topics.add("topic3");
        topics.add("topic4");
        topics.add("topic5");
        topics.add("topic6");
        topics.add("topic7");
        DeleteTopicsResult result = getAdmin().deleteTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取所有主题
     */
    @Test
    public void testListTopic(){
        ListTopicsResult result = getAdmin().listTopics();
        KafkaFuture<Set<String>> future = result.names();
        try {
            System.out.println("==================Kafka Topics====================");
            future.get().forEach(System.out::println);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
