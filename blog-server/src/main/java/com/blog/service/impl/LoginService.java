package com.blog.service.impl;

import com.blog.constant.MessageConstant;
import com.blog.constant.SessionConstant;
import com.blog.dto.UserLoginDTO;
import com.blog.entity.User;
import com.blog.exception.AccountOrPwdException;
import com.blog.exception.CaptchaExpiredException;
import com.blog.exception.CaptchaIncorrectException;
import com.blog.exception.CaptchaNotFoundException;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LoginService{
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 检验登录信息，并返回token
     * @param userLoginDTO
     * @return
     */
    public String loginCheck(UserLoginDTO userLoginDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.isNew()){
            session.invalidate();
            throw new CaptchaExpiredException(MessageConstant.CAPTCHA_NOT_FOUND);
        }else if(null != session.getAttribute(SessionConstant.UUID_KEY)){
            if((System.currentTimeMillis() - (Long)session.getAttribute(SessionConstant.START_KEY)) >= SessionConstant.SESSION_EXIST_TIME_SET){
                removeSessionAndAttribute(session);
                throw new CaptchaExpiredException(MessageConstant.CAPTCHA_EXPIRED);
            }
            String uuid = (String)session.getAttribute(SessionConstant.UUID_KEY);
            String code = (String)session.getAttribute(SessionConstant.CODE_KEY);
            if(!uuid.equals(userLoginDTO.getUuid()) ){
                throw new CaptchaExpiredException(MessageConstant.CAPTCHA_EXPIRED);
            }
            if(!(code.equals(userLoginDTO.getCode().toString()))) {
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

                removeSessionAndAttribute(session);
                return JwtUtil.createJWT(jwtProperties.getUserSecretKey()
                        , jwtProperties.getUserTtl()
                        , claims);
            }else{
                removeSessionAndAttribute(session);
                throw new AccountOrPwdException(MessageConstant.ACCOUNT_OR_PWD_ERROR);
            }

        }
        throw new CaptchaNotFoundException(MessageConstant.CAPTCHA_NOT_FOUND);
    }
    private void removeSessionAndAttribute(HttpSession session){
        session.removeAttribute(SessionConstant.UUID_KEY);
        session.removeAttribute(SessionConstant.CODE_KEY);
        session.removeAttribute(SessionConstant.START_KEY);
        session.invalidate();
    }
}
