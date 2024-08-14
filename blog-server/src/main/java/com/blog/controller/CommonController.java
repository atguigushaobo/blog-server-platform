package com.blog.controller;

import com.blog.constant.MessageConstant;
import com.blog.exception.FileUploadFailedException;
import com.blog.properties.FileProperties;
import com.blog.result.Result;
import com.blog.vo.FileUploadVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Autowired
    private FileProperties fileProperties;

    @PostMapping("/upload")
    public Result<FileUploadVO> upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new FileNotFoundException(MessageConstant.FILE_NOT_FOUND_EXCEPTION);
        }

        String originalFilename = file.getOriginalFilename();
        String extension = null;
        if (null != originalFilename) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID().toString().replace("-", "") + extension;

        File uploadDir = new File(fileProperties.getRealPath());
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) { // Create directories if they do not exist
                throw new IOException("Failed to create directory: " + fileProperties.getRealPath());
            }
        }

        String imagePath = uploadDir.getAbsolutePath() + File.separator + newFileName;
        log.info("文件上传至{}", imagePath);

        try {
            file.transferTo(new File(imagePath));
        } catch (IOException e) {
            throw new FileUploadFailedException(MessageConstant.FILE_UPLOAD_FAILED);
        }

        log.info("文件{}上传成功", originalFilename);

        // 拼接url
        String url = fileProperties.getUrl() + newFileName;
        log.info("图片访问路径{}", url);

        FileUploadVO fileUploadVO = FileUploadVO.builder()
                .newFileName(newFileName)
                .url(url)
                .originalFilename(originalFilename)
                .fileName("file")
                .build();
        return Result.success(fileUploadVO);
    }
}
