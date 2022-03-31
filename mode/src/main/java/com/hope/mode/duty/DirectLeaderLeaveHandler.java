package com.hope.mode.duty;

import lombok.extern.slf4j.Slf4j;

/**
 * 直接主管审批类
 * Created by lijin on  2022/3/11
 */
@Slf4j
public class DirectLeaderLeaveHandler extends AbstractLeaveHandler {
    public DirectLeaderLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.getLeaveDays() <= this.MIN) {
            log.info("直接主管:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if (null != this.nextHandler) {
            this.nextHandler.handlerRequest(request);
        } else {
            log.info("审批拒绝！");
        }

    }
}
