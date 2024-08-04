package com.blog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("blog.file")
public class FileProperties {
    private String realPath;//文件作为静态资源保存所在项目的路径设置
    private String url;//文件作为静态资源的对外访问路径
}
