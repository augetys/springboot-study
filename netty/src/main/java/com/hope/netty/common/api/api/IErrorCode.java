package com.hope.netty.common.api.api;

/**
 * 定义接口，获取错误码代码和信息
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
