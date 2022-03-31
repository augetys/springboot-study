package com.hope.kafka.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Created by lijin on  2022/3/29
 * 消息过滤器可以在消息抵达consumer之前被拦截
 * 实现了一个"过滤奇数、接收偶数"的过滤策略
 */
@Component
public class KafkaConsumerFilter {

    @Resource
    private ConsumerFactory<String, Object> consumerFactory;

    // 消息过滤器
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            return Integer.parseInt(consumerRecord.value().toString()) % 2 != 0;
            //返回true消息则被过滤
        });
        return factory;
    }
}
