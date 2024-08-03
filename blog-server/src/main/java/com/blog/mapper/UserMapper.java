package com.blog.mapper;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 条件查询用户信息
     * @return
     */
    List<User> getUser(User user);

    /**
     * 插入新用户信息
     * @param user
     * @return
     */
    Long insertUser(User user);
}
