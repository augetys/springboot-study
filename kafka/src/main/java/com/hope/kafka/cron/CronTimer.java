package com.hope.kafka.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lijin on  2022/3/29
 * 定时启动、停止监听器
 */
@EnableScheduling
@Component
@Slf4j
public class CronTimer {

    /**
     * @KafkaListener注解所标注的方法并不会在IOC容器中被注册为Bean， 而是会被注册在KafkaListenerEndpointRegistry中，
     * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
     **/
    @Resource
    private KafkaListenerEndpointRegistry registry;

    @Resource
    private ConsumerFactory<String, Object> consumerFactory;


    // 监听器容器工厂(设置禁止KafkaListener自启动)
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> container = new ConcurrentKafkaListenerContainerFactory<>();
        container.setConsumerFactory(consumerFactory);
        //禁止KafkaListener自启动
        container.setAutoStartup(false);
        return container;
    }


    // 定时启动监听器
    @Scheduled(cron = "0 10 17 * * ? ")
    public void startListener() {
        log.info("启动监听器...");
        // "timingConsumer"是@KafkaListener注解后面设置的监听器ID,标识这个监听器
        if (!registry.getListenerContainer("timingConsumer").isRunning()) {
            registry.getListenerContainer("timingConsumer").start();
        }
        //registry.getListenerContainer("timingConsumer").resume();
    }


    // 定时停止监听器
    @Scheduled(cron = "0 15 17 * * ? ")
    public void shutDownListener() {
        log.info("关闭监听器...");
        registry.getListenerContainer("timingConsumer").pause();
    }
}
