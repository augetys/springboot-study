package com.hope.netty.common.constant;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lijin on  2022/4/21
 */
public class CommonConstant {
    public final static String USER_TOKEN = "userId";

    public static Map<String, ChannelHandlerContext> onlineUserMap = new ConcurrentHashMap<>();
}
