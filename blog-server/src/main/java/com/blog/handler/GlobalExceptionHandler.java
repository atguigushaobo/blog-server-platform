package com.blog.handler;

import com.blog.constant.MessageConstant;
import com.blog.exception.BaseException;
import com.blog.exception.ImageGenerateFailedException;
import com.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(500,ex.getMessage());
    }
    @ExceptionHandler(FileNotFoundException.class)
    public Result fileNotFoundException(FileNotFoundException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(500, MessageConstant.FILE_NOT_FOUND_EXCEPTION);
    }
}
