package com.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaImageVO implements Serializable {
    private String uuid;
    private String image;//接收经过base64编码后的图片字符串
}
