package com.hope.mode.proxy.statics;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/10
 * 将目标对象注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。
 * 这样的话，我们就可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。
 * <p>
 * 缺点：接口一旦新增加方法，目标对象和代理对象都要进行修改，这是非常麻烦的！针对每个目标类都创建一个代理类
 */
@Slf4j
public class SmsImplProxyOne implements SmsServiceOne {

    private final SmsServiceOneImpl smsImplService;

    SmsImplProxyOne(SmsServiceOneImpl smsServiceImpl) {
        this.smsImplService = smsServiceImpl;
    }

    @Override
    public void send() {
        log.info("发送短信前");
        smsImplService.send();
        log.info("发送短信后");
    }
}
