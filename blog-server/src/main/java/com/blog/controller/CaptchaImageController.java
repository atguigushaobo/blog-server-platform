package com.blog.controller;

import com.blog.constant.MessageConstant;
import com.blog.exception.ImageGenerateFailedException;
import com.blog.result.Result;
import com.blog.service.impl.CaptchaImageService;
import com.blog.vo.CaptchaImageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/captchaImage")
@Slf4j
public class CaptchaImageController {
    @Autowired
    private CaptchaImageService captchaImageService;
    @GetMapping
    public Result<CaptchaImageVO> getCaptchaImage(HttpServletRequest request,HttpServletResponse response){
        CaptchaImageVO captchaImageVO = null;
        try {
            captchaImageVO = captchaImageService.getShearCaptcha(request,response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageGenerateFailedException(MessageConstant.IMAGEGENERATEFAILED);
        }
        return Result.success(captchaImageVO);
    }
}
