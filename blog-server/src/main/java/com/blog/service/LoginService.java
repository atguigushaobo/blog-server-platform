package com.blog.service;

import com.blog.dto.UserLoginDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    /**
     * 检验登录信息并返回token
     * @param userLoginDTO
     * @return
     */
    String loginCheck(UserLoginDTO userLoginDTO, HttpServletRequest request);
}
