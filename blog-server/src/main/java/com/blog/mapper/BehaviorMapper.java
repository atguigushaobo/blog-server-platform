package com.blog.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import com.blog.entity.*;
import org.apache.ibatis.annotations.Select;

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
    List<Comment> queryComment(Comment comment);
    /**
     * 新增评论
     */
    void addComment(Comment comment);
    /**
     * 删除评论
     */
    void delComment(Comment comment);
    /**
     * 获取文章点赞信息
     */
    @Select("select is_like from behaviour where user_id = #{userId} and article_id = #{articleId}")
    Integer isLike(Long userId,Long articleId);

    /**
     * 删除某文章的相关信息
     * @param articleId
     */
    @Delete("delete from behaviour where article_id = #{articleId}")
    void delBehaviorByArticleId(Long articleId);
}