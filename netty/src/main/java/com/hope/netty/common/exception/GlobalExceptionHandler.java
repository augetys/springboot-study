package com.hope.netty.common.exception;

import com.hope.netty.common.api.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理
 * Create by lijin on 2021/3/20 18:16
 * 异步方法中的异常不会被全局异常处理。
 *
 * 抛出的异常如果被代码内的 try/catch 捕获了，就不会被 ExceptionHandler 处理了
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public <T> CommonResult<T> handle(BusinessException e) {
        log.error("Exception", e);
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public <T> CommonResult<T> handle(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
                        "},errorMessage{" + fieldError.getDefaultMessage() + "}");
            });
        }
        return CommonResult.failed();
    }


    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    public <T> CommonResult<T> handleException(Exception e) {
        log.error("未定义异常:", e);
        return CommonResult.failed();
    }
}
