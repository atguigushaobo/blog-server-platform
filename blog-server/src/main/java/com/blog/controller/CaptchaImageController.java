package com.blog.controller;

import com.blog.constant.MessageConstant;
import com.blog.exception.ImageGenerateFailedException;
import com.blog.result.Result;
import com.blog.service.impl.CaptchaImageService;
import com.blog.vo.CaptchaImageVO;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/captchaImage")
public class CaptchaImageController {
    @Autowired
    private CaptchaImageService captchaImageService;
    @GetMapping
    public Result<CaptchaImageVO> getCaptchaImage(HttpServletResponse response){
        CaptchaImageVO captchaImageVO = null;
        try {
            captchaImageVO = captchaImageService.getShearCaptcha(response);
        } catch (IOException e) {
            throw new ImageGenerateFailedException(MessageConstant.IMAGEGENERATEFAILED);
        }
        return Result.success(captchaImageVO);
    }
}
