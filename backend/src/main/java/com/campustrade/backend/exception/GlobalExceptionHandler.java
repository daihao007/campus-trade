package com.campustrade.backend.exception;

import com.campustrade.backend.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e){
        log.error("运行时异常: {}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidationException(MethodArgumentNotValidException e){
        String message=e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return Result.error(400,message);
    }

    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e){
        String message = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return Result.error(400,message);
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e){
        log.error("未知异常: {}", e.getMessage(), e);
        return Result.error("服务器内部错误");
    }
}
