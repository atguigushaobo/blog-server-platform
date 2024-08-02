package com.blog.handler;

import com.blog.constant.MessageConstant;
import com.blog.exception.BaseException;
import com.blog.exception.ImageGenerateFailedException;
import com.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    /**
     * 捕获并处理我们抛出的业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ImageGenerateFailedException.class)
    public Result ImageGenerateFailedException(ImageGenerateFailedException ex){
        String msg = ex.getMessage();
        if(msg.contains(MessageConstant.IMAGEGENERATEFAILED)){
            return Result.error(500,msg);
        }
        return Result.error(500,msg);
    }
}
