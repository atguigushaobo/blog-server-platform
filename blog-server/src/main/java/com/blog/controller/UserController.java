package com.blog.controller;

import com.blog.dto.UserRegisterDTO;
import com.blog.result.Result;
import com.blog.service.impl.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关接口
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/regist")
    public Result regist(@RequestBody UserRegisterDTO userRegisterDTO){

        registerService.regist(userRegisterDTO);
        return Result.success(null);
    }
}
