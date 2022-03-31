package com.hope.kafka.producer;

import com.alibaba.fastjson.JSONObject;
import com.hope.kafka.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by lijin on  2022/3/25
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send/topic1")
    public void sendMessage() {
        kafkaTemplate.send("topic1", "李锦");
        kafkaTemplate.send("topic4", "1");
        kafkaTemplate.send("topic5", "万梦瑶");
        kafkaTemplate.send("topic6", JSONObject.toJSONString(new Student("小明", 2)));
    }

    @GetMapping("/send/topic3")
    public void sendMessage1() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("topic3", String.valueOf(i));
        }
    }

    @GetMapping("/send/topic7")
    public void sendMessage2() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("topic7", String.valueOf(i));
        }
    }


    /**
     * 回调方法addCallback，我们可以在回调方法中监控消息是否发送成功 或 失败时做补偿处理，有两种写法，
     *
     * @param callbackMessage
     */
    @GetMapping("/send/callback/{message}")
    public void sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            log.info("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            log.info("发送消息失败:" + failure.getMessage());
        });
    }

    /**
     * 回调第二种写法
     *
     * @param callbackMessage
     */
    @GetMapping("/kafka/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }
}
