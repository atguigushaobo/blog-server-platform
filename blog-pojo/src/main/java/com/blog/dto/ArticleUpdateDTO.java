package com.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleUpdateDTO {
    private Long id;
    private String title;
    private String content;
    private String image;
    private Date updateTime;
}
