package com.blog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("blog.captcha.image")
@Data
public class CaptchaImageProperties {
    private Integer width;//图片的宽度
    private Integer height;//图片的高度
    private Integer codeCount;//随机生成的验证码位数
    private Integer thickness;//图片干扰线宽度

    //图片背景颜色相关配置
    private Integer colorR;
    private Integer colorG;
    private Integer colorB;
}
