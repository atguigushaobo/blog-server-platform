package com.blog.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Classname: SelectArticle
 * Description:
 *用来完成查询文章
 * @Author tt
 * @Creat 2024/8/9 20:24
 * @Version 17
 */
@Data
public class SelectArticle {
    private Long id;

    private String title;

    private String content;

    private String username;

    private String image;

    private Integer articleLike;

}
