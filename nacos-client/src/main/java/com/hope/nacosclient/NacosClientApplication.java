package com.hope.nacosclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Create by lijin on 2022/2/24 19:25
 */
@SpringBootApplication
public class NacosClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class, args);
    }

}
