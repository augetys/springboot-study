package com.hope.netty.chat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.netty.chat.service.ChatService;
import com.hope.netty.common.constant.CommonConstant;
import com.hope.netty.common.ennms.ChatTypeEnum;
import com.hope.netty.common.ennms.UserTypeEnum;
import com.hope.netty.sys.mapper.SysUserMapper;
import com.hope.netty.sys.model.SysUser;
import com.hope.netty.sys.service.SysUserService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by lijin on  2022/4/22
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserService sysUserService;

    @Override
    public void register(JSONObject jsonObject, ChannelHandlerContext ctx) {
        String userId = (String) jsonObject.get("userId");
        SysUser sysUser = sysUserService.getUserByUserId(userId);
        CommonConstant.onlineUserMap.put(userId, ctx);
        log.info("userId为 {} 的用户登记到在线用户表，当前在线人数为：{}", userId, CommonConstant.onlineUserMap.size());
        log.info("当前channelId为：{}", ctx.channel().id());
        // 用户连接
        if (sysUser.getType().equals(UserTypeEnum.USER.getCode())) {
            List<String> call = this.onlineCall();
            if (CollectionUtils.isEmpty(call)) {
                sendMessage(ctx, "客服忙线");
            } else {
                // 分配在线客服
                Random random = new Random();
                int n = random.nextInt(call.size());
                sendMessage(ctx, MessageFormat.format("编号为 {0} 的客服在线为您服务！", call.get(n)));
            }
        } else if (sysUser.getType().equals(UserTypeEnum.CALL.getCode())) {
            // 客服连接
            sendMessage(ctx, MessageFormat.format("编号为 {0} 的客服已上线！", userId));
        }
    }

    @Override
    public void singleSend(JSONObject jsonObject, ChannelHandlerContext ctx) {
        String fromUserId = (String) jsonObject.get("fromUserId");
        String toUserId = (String) jsonObject.get("toUserId");
        String content = (String) jsonObject.get("content");
        ChannelHandlerContext toUserCtx = CommonConstant.onlineUserMap.get(toUserId);
        if (toUserCtx == null) {
            sendMessage(ctx, MessageFormat.format("userId为 {0} 的用户没有登录！", toUserId));
        } else {
            JSONObject message = new JSONObject();
            message.put("fromUserId", fromUserId);
            message.put("content", content);
            message.put("type", ChatTypeEnum.SINGLE.getCode());
            sendMessage(toUserCtx, JSON.toJSONString(message));
        }
    }

    @Override
    public void groupSend(JSONObject jsonObject, ChannelHandlerContext ctx) {

    }

    @Override
    public List<SysUser> userList() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", 1);
        return sysUserMapper.selectList(queryWrapper);
    }

    /**
     * 信息发送
     *
     * @param ctx     用户对应管道
     * @param message 消息
     */
    private void sendMessage(ChannelHandlerContext ctx, String message) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }

    /**
     * 获取在线客服
     *
     * @return
     */
    public List<String> onlineCall() {
        // 获取客服列表
        List<String> list = userList().stream().map(SysUser::getId).collect(Collectors.toList());
        List<String> call = new ArrayList<>();
        // 获取在线客服
        for (Map.Entry<String, ChannelHandlerContext> map : CommonConstant.onlineUserMap.entrySet()) {
            String key = map.getKey();
            if (list.contains(key)) {
                call.add(key);
            }
        }
        return call;
    }
}
