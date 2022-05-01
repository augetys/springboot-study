package com.hope.netty;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lijin on  2022/4/14
 */
@SpringBootTest
@Slf4j
public class NettyApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("3");
        list.add("4");
        List<String> call = new ArrayList<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "李四");
        map.put("2", "张三");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (list.contains(key)) {
                call.add(key);
            }
        }
        System.out.println(call);
    }
}

