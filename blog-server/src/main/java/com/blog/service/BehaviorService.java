package com.blog.service;
import com.blog.entity.*;

import java.util.List;

public interface BehaviorService {
    /**
     * 点赞，取消点赞
     */
    String likeOperate(Behavior behavior);
    /**
     * 查询所有评论
     */
    List<Comment> queryComment(Long articleId);
    /**
    * 新增评论
    */
    void addComment(Comment comment);
    /**
    * 删除评论
     */
    void delComment(Long commentId);

}