package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Behavior {
    private Long behaviorId;
    private Long articleId;
    private Long userId;
    private Integer isLike;//默认为0,点赞后为1
}
