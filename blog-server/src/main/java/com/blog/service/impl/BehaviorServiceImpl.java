package com.blog.service.impl;
import com.blog.context.BaseContext;
import com.blog.entity.*;
import com.blog.exception.DeleteCommentException;
import com.blog.exception.IsLikeParamException;
import com.blog.exception.ParamException;
import com.blog.mapper.BehaviorMapper;
import com.blog.mapper.BehaviorMapper;
import com.blog.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BehaviorServiceImpl implements BehaviorService {
    @Autowired
    private BehaviorMapper behaviorMapper;
    /**
     * 点赞，取消点赞
     */
    @Override
    public void likeOperate(Behavior behavior){
        if(behavior.getIsLike()==null||behavior.getArticleId()==null)
        {
            throw new ParamException("参数不能为空！");
        }
        else if(behavior.getIsLike()!=0 && behavior.getIsLike()!=1){
            throw new IsLikeParamException("isLike只能为0或1！");
        }
        behavior.setUserId(BaseContext.getCurrentId());
        if(behaviorMapper.behaviorExist(behavior)!=null){
            behaviorMapper.behaviorUpdate(behavior);
        }
        else {
            behaviorMapper.behaviorInsert(behavior);
        }
    }
    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> queryComment(Long articleId){
        List<Comment> comments = behaviorMapper.queryComment(articleId);
        System.out.println(comments);
        return comments;
    }
    /**
     * 新增评论
     */
    @Override
    public void addComment(Comment comment){
        System.out.println(comment);
        if(comment.getCommentBody()==null||comment.getArticleId()==null)
        {
            throw new ParamException("参数不能为空！");
        }
        comment.setUserId(BaseContext.getCurrentId());
        comment.setCreateTime(new Date());
        behaviorMapper.addComment(comment);
    }
    /**
     * 删除评论
     */
    @Override
    public void delComment(long commentId) {
        if(commentId==BaseContext.getCurrentId()){
            behaviorMapper.delComment(commentId);
        }
        else {
            throw new DeleteCommentException("只能删除自己的评论!");
        }
    }
}