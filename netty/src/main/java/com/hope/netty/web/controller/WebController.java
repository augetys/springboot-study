package com.hope.netty.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lijin on  2022/4/14
 */
@RequestMapping("")
@Controller
public class WebController {
    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @GetMapping("/user")
    public String chatroom() {
        return "user";
    }

    @GetMapping("/call")
    public String chatroom1() {
        return "call";
    }
}
