package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article implements Serializable {
    private Long id;//自增主键
    private String title;
    private String content;
    private Integer userId;
    private String username;
    private String image;
    private Integer articleLike;
    private Date publicTime;
    private Date updateTime;
}
