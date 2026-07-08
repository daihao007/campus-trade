package com.campustrade.backend.exception;

import com.campustrade.backend.common.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e){
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
        return Result.error("服务器内部错误");
    }
}
