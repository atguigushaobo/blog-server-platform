package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {
    @JsonIgnore
    private Long commentId;//自增主键
    private String commentBody;
    private Date createTime;
    private Long userId;
    private String username;
    @JsonIgnore
    private Long articleId;//可能在联查的时候需要

    @JsonIgnore
    public Long getArticleId() {
        return articleId;
    }
    @JsonProperty
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
