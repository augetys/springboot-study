package com.hope.mode.duty;

/**
 * 部门经理审批类
 * Created by lijin on  2022/3/11
 */
public class DeptManagerLeaveHandler extends AbstractLeaveHandler {
    public DeptManagerLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.getLeaveDays() <= this.MIN && request.getLeaveDays() <= this.MIDDLE) {
            System.out.println("部门经理:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if (null != this.nextHandler) {
            this.nextHandler.handlerRequest(request);
        } else {
            System.out.println("审批拒绝！");
        }

    }
}
