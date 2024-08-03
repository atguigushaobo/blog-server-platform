package com.blog.service;

import com.blog.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 条件查询获取所需用户
     * @param user
     * @return
     */
    List<User> getUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    void insertUser(User user);
}
