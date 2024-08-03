package com.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleUpdateDTO implements Serializable {
    private Long id;
    private String title;
    private String content;
    private String image;
    private Date updateTime;
}
