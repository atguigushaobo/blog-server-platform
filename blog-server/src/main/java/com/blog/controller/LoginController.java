package com.blog.controller;

import com.blog.dto.UserLoginDTO;
import com.blog.result.Result;
import com.blog.service.impl.CaptchaImageService;
import com.blog.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;


    /**
     * 登录成功返回token
     * @param userLoginDTO
     * @return
     */
    @PostMapping
    public Result<String> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request){
        String token = loginService.loginCheck(userLoginDTO,request);
        return Result.success(token);
    }
}
