package com.hope.mode.duty;

/**
 * 总经理审批类
 * Created by lijin on  2022/3/11
 */
public class GManagerLeaveHandler extends AbstractLeaveHandler {
    public GManagerLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.getLeaveDays() > this.MIDDLE && request.getLeaveDays() <= this.MAX) {
            System.out.println("总经理经理:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if (null != this.nextHandler) {
            this.nextHandler.handlerRequest(request);
        } else {
            System.out.println("审批拒绝！");
        }

    }
}
