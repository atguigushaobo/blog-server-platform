package com.blog.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.blog.entity.*;

import java.util.List;

@Mapper
public interface BehaviorMapper {
    /**
     * 判断行为是否存在
     */
    Long behaviorExist(Behavior behavior);
    /**
     * 新增行为
     */
    void behaviorInsert(Behavior behavior);
    /**
     * 修改行为
     */
    void behaviorUpdate(Behavior behavior);
    /**
     * 查询所有评论
     */
    List<Comment> queryComment(Long commentId);
    /**
     * 新增评论
     */
    void addComment(Comment comment);
    /**
     * 删除评论
     */
    void delComment(Long articleId);
}