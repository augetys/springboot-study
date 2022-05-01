package com.hope.netty.chat.service;

import com.alibaba.fastjson.JSONObject;

import com.hope.netty.sys.model.SysUser;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by lijin on  2022/4/22
 */
public interface ChatService {
    /**
     * 用戶和channel綁定
     * @param jsonObject
     * @param ctx
     */

    void register(JSONObject jsonObject, ChannelHandlerContext ctx);
    /**
     * 单聊
     *
     * @param jsonObject 内容主题
     * @param ctx        channel上下文
     */
    public void singleSend(JSONObject jsonObject, ChannelHandlerContext ctx);

    /**
     * 群聊
     *
     * @param jsonObject
     * @param ctx
     */
    public void groupSend(JSONObject jsonObject, ChannelHandlerContext ctx);


    /**
     * 获取在线用户列表
     *
     * @return
     */
    public List<SysUser> userList();

}
