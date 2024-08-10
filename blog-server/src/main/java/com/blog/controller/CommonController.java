package com.blog.controller;

import com.blog.constant.MessageConstant;
import com.blog.exception.FileUploadFailedException;
import com.blog.properties.FileProperties;
import com.blog.result.Result;
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
    private FileProperties fileProperties;
    @PostMapping("/upload")
    public Result<FileUploadVO> upload(MultipartFile file) throws FileNotFoundException {
        if(file.isEmpty()){
            throw new FileNotFoundException(MessageConstant.FILE_NOT_FOUND_EXCEPTION);
        }
        String originalFilename = file.getOriginalFilename();
        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replace("-","") + extention;
        File imagesFile = new File(fileProperties.getRealPath());//通过相对路径获取绝对路径
        if(!imagesFile.exists()){  // 不存在，则创建该文件夹
            imagesFile.mkdir();
        }
        String realPath = imagesFile.getAbsolutePath();
        String imagePath = realPath + File.separator + newFileName;
        log.info("文件上传至{}",imagePath);
        try {
            file.transferTo(new File(imagePath));//需要注意的是我们的transferTo方法只能调用一次，多次会抛出异常
            String targetPath = (this.getClass().getClassLoader().getResource("") + "static/images/" + newFileName).replace("/","\\").replace("file:\\","");
            FileCopyUtils.copy(new File(imagePath),new File(targetPath));
        } catch (IOException e) {
            throw new FileUploadFailedException(MessageConstant.FILE_UPLOAD_FAILED);
        }
        log.info("文件{}上传成功",originalFilename);
        //拼接url
        String url = fileProperties.getUrl() + newFileName;
        log.info("图片访问路径{}",url);
        FileUploadVO fileUploadVO = FileUploadVO.builder()
                .newFileName(newFileName)
                .url(url)
                .originalFilename(originalFilename)
                .fileName("file")
                .build();
        return Result.success(fileUploadVO);
    }
}
