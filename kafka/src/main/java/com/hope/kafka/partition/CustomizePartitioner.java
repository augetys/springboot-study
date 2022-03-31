package com.hope.kafka.partition;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lijin on  2022/3/25
 * 自定义分区需要配置
 * 分区策略
 * ① 若发送消息时指定了分区（即自定义分区策略），则直接将消息append到指定分区；
 * ② 若发送消息时未指定 patition，但指定了 key（kafka允许为每条消息设置一个key），则对key值进行hash计算，根据计算结果路由到指定分区，这种情况下可以保证同一个 Key 的所有消息都进入到相同的分区；
 * ③ patition 和 key 都未指定，则使用kafka默认的分区策略，轮询选出一个 patition；
 */
@Component
@Slf4j
public class CustomizePartitioner implements Partitioner {

    /**
     * 自定义分区策略
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 自定义分区规则(这里假设全部发到0号分区)
        return 0;
    }

    /**
     * 在分区程序关闭时调用
     */
    @Override
    public void close() {
       log.info("close ...");
    }

    /**
     * 做必要的初始化工作
     */
    @Override
    public void configure(Map<String, ?> configs) {
       log.info("init ...");
    }
}
