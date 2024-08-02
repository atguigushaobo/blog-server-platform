package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;//自增主键
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer sex;
    private Date createTime;
    private Date updateTime;
    private Integer status;//逻辑删除字段，默认为1代表存在，0代表删除
}
