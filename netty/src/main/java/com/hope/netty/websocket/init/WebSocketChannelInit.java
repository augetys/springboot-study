package com.hope.netty.websocket.init;
import com.hope.netty.websocket.handler.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author lixl
 * @ClassName: NettyChannelInitializer
 * @Description: 添加处理的Handler，通常包括消息编解码、业务处理，也可以是日志、权限、过滤等
 * @date 2019年4月8日 下午1:58:33
 */
@Component
public class WebSocketChannelInit extends ChannelInitializer<SocketChannel> {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //对http协议的支持.http编码解码器
        pipeline.addLast(new HttpServerCodec());
        // 方便大文件传输，不过实质上都是短的文本数据
        pipeline.addLast(new ChunkedWriteHandler());
        //post请求分三部分. request line / request header / message body
        // HttpObjectAggregator将多个信息转化成单一的request或者response对象
        pipeline.addLast(new HttpObjectAggregator(65536));
        // 将http协议升级为ws协议. websocket的支持 若添加此处，则WebSocketHandler不用处理FullHttpRequest
        // pipeline.addLast(new WebSocketServerProtocolHandler(path));
        // 自定义处理handler
        pipeline.addLast(webSocketHandler);
    }
}
