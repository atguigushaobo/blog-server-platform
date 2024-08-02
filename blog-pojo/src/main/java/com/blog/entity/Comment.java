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
public class Comment implements Serializable {
    private Long commentId;//自增主键
    private String commentBody;
    private Date createTime;
    private Long userId;
    private String username;
    private Long articleId;//可能在联查的时候需要
}
