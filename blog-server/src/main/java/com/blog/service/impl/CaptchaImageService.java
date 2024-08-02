package com.blog.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.blog.properties.CaptchaImageProperties;
import com.blog.vo.CaptchaImageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import static cn.hutool.core.img.ImgUtil.toBufferedImage;

@Service
@Slf4j
public class CaptchaImageService {
    @Autowired
    private CaptchaImageProperties captchaImageProperties;
    private static final Integer EXPIRED_TIME = 60;//设置session的过期时间，即我们图片验证码的过期时间
    public CaptchaImageVO getShearCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaImageVO captchaImageVO = new CaptchaImageVO();

        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(captchaImageProperties.getWidth()
                , captchaImageProperties.getHeight()
                ,captchaImageProperties.getCodeCount()
                ,captchaImageProperties.getThickness());
        //设置背景颜色
        shearCaptcha.setBackground(new Color(captchaImageProperties.getColorR()
                , captchaImageProperties.getColorG()
                , captchaImageProperties.getColorB()));
        //生成四位验证码
        String code = RandomUtil.randomNumbers(captchaImageProperties.getCodeCount());
        //生成验证码图片
        Image image = shearCaptcha.createImage(code);
        //准备返回信息
        String uuid = UUID.randomUUID().toString();
        captchaImageVO.setUuid(uuid);
        //返回验证码信息
        captchaImageVO.setImage(responseCode(response, code, image,uuid));
        //利用session存储我们的uuid于服务器中，用于校验登录时的图片验证码信息
        HttpSession session = request.getSession();
        if(session.isNew()){
            session.setAttribute("code",code);
            session.setAttribute("uuid",uuid);
            session.setMaxInactiveInterval(EXPIRED_TIME);
        }else{
            session.invalidate();
            session = request.getSession();
            session.setAttribute("code",code);
            session.setAttribute("uuid",uuid);
            session.setMaxInactiveInterval(EXPIRED_TIME);
        }
        log.info("生成图片验证码uuid:{}",uuid);
        return captchaImageVO;
    }

    private static String responseCode(HttpServletResponse response, String code, Image image,String uuid) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        String uuidStr = uuid.replace("-", "");
        log.info("生成验证码 uuidStr：{} ，code:{}",uuidStr, code);
        response.setHeader("verifyCodeUuid",uuidStr);

        BufferedImage bufferedImage = toBufferedImage(image);
        // 创建一个ByteArrayOutputStream，用于存储图片数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 写入图片数据到ByteArrayOutputStream
        ImageIO.write(bufferedImage, "jpeg", baos);
        // 将ByteArrayOutputStream转换为ByteArrayInputStream
        byte[] imageInBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageInBytes);
        //输出
//            IoUtil.write(response.getOutputStream(), true, imageInBytes);

    }
}
