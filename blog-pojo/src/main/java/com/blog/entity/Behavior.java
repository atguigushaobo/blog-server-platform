package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Behavior implements Serializable {
    private Long behaviorId;
    private Long articleId;
    private Long userId;
    private Integer isLike;//默认为0,点赞后为1
}
