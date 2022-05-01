package com.hope.netty.common.exception;


import com.hope.netty.common.api.api.IErrorCode;

/**
 * 自定义业务类型异常
 * Create by lijin on 2021/3/20 18:27
 */
public class BusinessException extends RuntimeException {
    private IErrorCode errorCode;

    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
