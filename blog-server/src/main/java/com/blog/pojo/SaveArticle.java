package com.blog.pojo;

import lombok.Data;

/**
 * Classname: SaveArticle
 * Description:
 *接收前端传来的参数,用来完成新增文章
 * @Author tt
 * @Creat 2024/8/11 10:18
 * @Version 17
 */
@Data
public class SaveArticle {
    private String title;
    private String content;
    private String image;
}
