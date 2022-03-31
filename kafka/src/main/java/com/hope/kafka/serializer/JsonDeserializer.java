package com.hope.kafka.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * 自定义kafkaz中JSOBObject类型的反序列化方式
 */
public class JsonDeserializer implements Deserializer<JSONObject> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public JSONObject deserialize(String topic, byte[] data) {
        return JSON.parseObject(data,JSONObject.class);
    }

    @Override
    public void close() {

    }
}