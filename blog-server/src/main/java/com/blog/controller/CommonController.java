package com.blog.controller;

import com.blog.constant.MessageConstant;
import com.blog.exception.FileUploadFailedException;
import com.blog.properties.FileProperties;
import com.blog.result.Result;
import com.blog.service.impl.CommonService;
import com.blog.vo.FileUploadVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Autowired
    private CommonService commonService;

    @PostMapping("/upload")
    public Result<FileUploadVO> upload(MultipartFile file) throws FileNotFoundException {
        return Result.success(commonService.upload(file));
    }
}