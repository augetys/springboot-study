package com.hope.mode.duty;

import lombok.extern.slf4j.Slf4j;

/**
 * 总经理审批类
 * Created by lijin on  2022/3/11
 */
@Slf4j
public class GManagerLeaveHandler extends AbstractLeaveHandler {
    public GManagerLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.getLeaveDays() > this.MIDDLE && request.getLeaveDays() <= this.MAX) {
            log.info("总经理经理:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if (null != this.nextHandler) {
            this.nextHandler.handlerRequest(request);
        } else {
            log.info("审批拒绝！");
        }

    }
}
