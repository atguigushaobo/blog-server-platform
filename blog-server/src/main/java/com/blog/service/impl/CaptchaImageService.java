package com.blog.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.blog.vo.CaptchaImageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
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
    public CaptchaImageVO getShearCaptcha(HttpServletResponse response) throws IOException {
        CaptchaImageVO captchaImageVO = new CaptchaImageVO();
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 50,4,3);
        //设置背景颜色
        shearCaptcha.setBackground(new Color(249, 251, 220));
        //生成四位验证码
        String code = RandomUtil.randomNumbers(4);
        //生成验证码图片
        Image image = shearCaptcha.createImage(code);

        //准备返回信息
        //TODO 将生成的uuid存入我们的redis或者其他容器中，在登录的时候进行校验
        String uuid = UUID.randomUUID().toString();
        captchaImageVO.setUuid(uuid);
        //返回验证码信息
        captchaImageVO.setImage(responseCode(response, code, image,uuid));
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
