package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    private Long articleId;

    private String title;

    private String content;

    private Long userId;

    private String image;

    private Date publishTime;

    private Date updateTime;

    private Integer articleLike;

    private static final long serialVersionUID = 1L;
}