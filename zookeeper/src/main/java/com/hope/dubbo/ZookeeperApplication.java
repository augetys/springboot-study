package com.hope.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Create by lijin on 2022/2/24 19:25
 */
@SpringBootApplication
public class ZookeeperApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }

}
