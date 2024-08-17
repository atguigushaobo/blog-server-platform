package com.blog.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Classname: ShowArticle
 * Description:
 *用来存储查询文章详情的实体类
 * @Author tt
 * @Creat 2024/8/11 18:35
 * @Version 17
 */
@Data
public class ShowArticle {
    private Long id;

    private String title;

    private String content;

    private Long userId;

    private String username;

    private String image;

    private Integer articleLike;

    private Date publishTime;

    private Integer commentCount;

}
