package com.blog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer sex;
}
