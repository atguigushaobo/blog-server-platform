package com.blog.controller;

import com.blog.result.Result;
import com.blog.vo.FileUploadVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
public class CommonController {
    @PostMapping("/upload")
    public Result<FileUploadVO> upload(MultipartFile file){

        return null;
    }

}
