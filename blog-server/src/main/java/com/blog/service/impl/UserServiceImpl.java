package com.blog.service.impl;

import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 条件查询用户信息
     * @param user
     * @return
     */
    @Override
    public List<User> getUser(User user) {
        return userMapper.getUser(user);
    }

    /**
     * 插入注册用户信息
     * @param user
     * @return
     */
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
