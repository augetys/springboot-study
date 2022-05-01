package com.hope.netty.websocket.init;

import javax.annotation.PostConstruct;

import com.hope.netty.websocket.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InitService {

	@Autowired
	private NettyServer webSocketServer;

	@PostConstruct
	void init() {
		new Thread(() -> {
            // 消息推送服务端
            webSocketServer.init();
        }).start();
	}
}
