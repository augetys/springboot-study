package com.hope.mode.duty;

/**
 * 抽象处理类
 * Created by lijin on  2022/3/11
 */
public abstract class AbstractLeaveHandler {
    /**直接主管审批处理的请假天数*/
    protected int MIN = 1;
    /**部门经理处理的请假天数*/
    protected int MIDDLE = 3;
    /**总经理处理的请假天数*/
    protected int MAX = 30;

    /**领导名称*/
    protected String handlerName;

    /**下一个处理节点（即更高级别的领导）*/
    protected AbstractLeaveHandler nextHandler;

    /**设置下一节点*/
    protected void setNextHandler(AbstractLeaveHandler handler){
        this.nextHandler = handler;
    }

    /**处理请假的请求，子类实现*/
    protected void handlerRequest(LeaveRequest request){

    }
}
