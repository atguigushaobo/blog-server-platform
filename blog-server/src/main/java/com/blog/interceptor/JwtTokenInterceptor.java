package com.blog.interceptor;

import com.blog.context.BaseContext;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerAdapter)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        String token = request.getHeader(jwtProperties.getUserTokenName());
        try{
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            //这里看了源码我们知道了如果token是null的话，该方法内部会抛出异常，我们只需要在外部进行捕获处理即可
            Long userId = Long.valueOf(claims.get("userId").toString());
            log.info("jwt校验当前用户id:{}",userId);
            BaseContext.setCurrentId(userId);
            return true;
        }catch(Exception e){
            response.setStatus(401);//代表未授权访问
            return false;
        }
    }
}
