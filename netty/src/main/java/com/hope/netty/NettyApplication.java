package com.hope.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Create by lijin on 2022/2/24 19:25
 */
@SpringBootApplication
public class NettyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

}
