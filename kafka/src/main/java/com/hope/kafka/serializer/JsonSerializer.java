package com.hope.kafka.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;


import java.util.Map;

/**
 * 自定义kafka中JSONObject类型的序列化方式
 */
public class JsonSerializer implements Serializer<JSONObject> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, JSONObject data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public void close() {

    }
}