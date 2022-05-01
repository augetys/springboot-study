package com.hope.netty.websocket.server;

import com.hope.netty.websocket.init.WebSocketChannelInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 服务端实现步骤:
 * 1. 创建bossGroup线程组: 处理网络事件--连接事件
 * 2. 创建workerGroup线程组: 处理网络事件--读写事件
 * 3. 创建服务端启动助手
 * 4. 设置bossGroup线程组和workerGroup线程组
 * 5. 设置服务端通道实现为NIO
 * 6. 参数设置
 * 7. 创建一个通道初始化对象
 * 8. 向pipeline中添加自定义业务处理handler
 * 9. 启动服务端并绑定端口,同时将异步改为同步
 * 10. 关闭通道和关闭连接池
 */
@Component
@Slf4j
public class NettyServer implements DisposableBean {


    @Autowired
    private WebSocketChannelInit webSocketChannelInit;

    /**
     * boos线程组
     */
    private EventLoopGroup boos;

    /**
     * work线程组
     */
    private EventLoopGroup work;

    @Value("${netty.port}")
    private int port;

    /**
     * netty服务启动
     */
    public void init() {
        log.info("正在启动netty服务器");
        boos = new NioEventLoopGroup();
        work = new NioEventLoopGroup();
        // 创建启动助手
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boos, work)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler())
                .childHandler(webSocketChannelInit);

        // 绑定ip和端口启动服务端
        ChannelFuture sync = null;
        try {
            // 绑定netty的启动端口
            sync = serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            close();
        }
        log.info("netty服务器启动成功" + "--端口:" + port);
        sync.channel().closeFuture();
    }

    /**
     * 容器销毁前关闭线程组
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        close();
    }

    /**
     * 关闭方法
     */
    public void close() {
        if (boos != null) {
            boos.shutdownGracefully();
        }
        if (work != null) {
            work.shutdownGracefully();
        }
    }
}
