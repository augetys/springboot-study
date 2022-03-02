package com.hope.nacosclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lijin on  2022/3/2
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${nacos.config}")
    private String config;

    /**
     * http://localhost:8898/config/get
     */
    @RequestMapping("/get")
    public String get() {
        return config;
    }

}
