package com.blog.service.impl;

import com.blog.constant.MessageConstant;
import com.blog.context.BaseContext;
import com.blog.dto.UserLoginDTO;
import com.blog.entity.User;
import com.blog.exception.AccountOrPwdException;
import com.blog.exception.CaptchaExpiredException;
import com.blog.exception.CaptchaIncorrectException;
import com.blog.exception.CaptchaNotFoundException;
import com.blog.properties.JwtProperties;
import com.blog.service.LoginService;
import com.blog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.blog.constant.MessageConstant.CAPTCHA_ERROR;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 检验登录信息，并返回token
     * @param userLoginDTO
     * @return
     */
    @Override
    public String loginCheck(UserLoginDTO userLoginDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.isNew()){
            throw new CaptchaExpiredException(MessageConstant.CAPTCHA_EXPIRED);
        }else if(!session.isNew() && null != session.getAttribute("uuid")){
            String uuid = (String)session.getAttribute("uuid");
            Integer code = (Integer)session.getAttribute("code");
            if(!uuid.equals(userLoginDTO.getUuid()) ){
                throw new CaptchaExpiredException(MessageConstant.CAPTCHA_EXPIRED);
            }
            if(!(code == userLoginDTO.getCode())){
                throw new CaptchaIncorrectException(MessageConstant.CAPTCHA_ERROR);
            }
            //条件查询
            User queryUser = User.builder()
                    .username(userLoginDTO.getUsername())
                    .password(DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes()))
                    .build();
            List<User> list = userService.getUser(queryUser);
            if(null != list && list.size() > 0){
                User user = list.get(0);
                log.info("用户{}登录成功",user.getUsername());
                Long userId = user.getId();
                Map<String,Object> claims = new HashMap<>();
                claims.put("userId",userId);
                return JwtUtil.createJWT(jwtProperties.getUserSecretKey()
                        , jwtProperties.getUserTtl()
                        , claims);
            }else{
                throw new AccountOrPwdException(MessageConstant.ACCOUNT_OR_PWD_ERROR);
            }

        }
        throw new CaptchaNotFoundException(MessageConstant.CAPTCHA_NOT_FOUND);
    }
}
