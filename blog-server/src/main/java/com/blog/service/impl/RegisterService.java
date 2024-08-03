package com.blog.service.impl;

import cn.hutool.core.date.DateTime;
import com.blog.constant.MessageConstant;
import com.blog.dto.UserRegisterDTO;
import com.blog.entity.User;
import com.blog.exception.RegistFailedException;
import com.blog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
@Slf4j
public class RegisterService {
    @Autowired
    private UserMapper userMapper;
    public void regist(UserRegisterDTO userRegisterDTO){
        User userQueryWrapper = new User();
        userQueryWrapper.setUsername(userRegisterDTO.getUsername());
        List<User> list = userMapper.getUser(userQueryWrapper);
        if(null != list && list.size() > 0){
            throw new RegistFailedException(MessageConstant.ACCOUNT_ALLREADY_EXIST);
        }
        User user = User.builder()
                .username(userRegisterDTO.getUsername())
                .password(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()))
                .phone(userRegisterDTO.getPhone())
                .email(userRegisterDTO.getEmail())
                .sex(userRegisterDTO.getSex())
                .createTime(DateTime.now())
                .updateTime(DateTime.now())
                .build();
        userMapper.insertUser(user);
        log.info("用户{}注册成功",user.getId());
    }

}
