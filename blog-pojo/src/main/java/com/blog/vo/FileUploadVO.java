package com.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片上传后，需要相应给前端的信息封装类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadVO implements Serializable {
    private String fileName;//接收文件时，形参需要的命名
    private String newFileName;//上传后文件的命名
    private String url;//图片上传后的访问路径
    private String originalFilename;//上传文件的起始文件名
}
