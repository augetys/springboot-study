package com.hope.kafka.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;

import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import java.util.Map;


/**
 * Created by lijin on  2022/3/25
 */
@Configuration
public class KafkaInitialConfiguration {

    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("消费异常：" + message.getPayload());
            return null;
        };
    }

    /**
     * 解决批量消费的问题
     * @param properties 配置信息，springboot 从配置文件获取, 自动注入
     * @return 批量工厂类
     */
    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(KafkaProperties properties) {
        Map<String, Object> consumerProperties = properties.buildConsumerProperties();
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerProperties));
        // 开启批量监听
        factory.setBatchListener(true);
        return factory;
    }
}
