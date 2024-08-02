package com.blog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    private String username;
    private String password;
    private String uuid;//验证码信息
    private Integer code;
}
