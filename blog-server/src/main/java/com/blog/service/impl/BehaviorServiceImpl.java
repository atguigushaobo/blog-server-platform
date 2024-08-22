package com.blog.service.impl;
import com.blog.context.BaseContext;
import com.blog.entity.*;
import com.blog.exception.DeleteCommentException;
import com.blog.exception.IsLikeParamException;
import com.blog.exception.NoSuchCommentException;
import com.blog.exception.ParamException;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.BehaviorMapper;
import com.blog.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.blog.constant.MessageConstant.NO_SUCH_COMMENT_ERROR;

@Service
public class BehaviorServiceImpl implements BehaviorService {
    @Autowired
    private BehaviorMapper behaviorMapper;
    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 点赞，取消点赞
     */
    @Override
    public String likeOperate(Behavior behavior){
        if(behavior.getIsLike()==null||behavior.getArticleId()==null)
        {
            throw new ParamException("参数不能为空！");
        }
        else if(behavior.getIsLike()!=0 && behavior.getIsLike()!=1){
            throw new IsLikeParamException("isLike只能为0或1！");
        }
        behavior.setUserId(BaseContext.getCurrentId());
        if(behaviorMapper.behaviorExist(behavior)!=null){
            Article articleWrapper = new Article();
            articleWrapper.setId(behavior.getArticleId());
            Article article = articleMapper.getArticle(articleWrapper).get(0);
            if(behavior.getIsLike() > behaviorMapper.isLike(behavior.getArticleId())){
                article.setArticleLike(article.getArticleLike() + 1);
                behaviorMapper.behaviorUpdate(behavior);
                articleMapper.updateArticle(article);
                return "点赞成功";
            }else if(behavior.getIsLike() < behaviorMapper.isLike(behavior.getArticleId())){
                article.setArticleLike(article.getArticleLike() - 1);
                behaviorMapper.behaviorUpdate(behavior);
                articleMapper.updateArticle(article);
                return "取消点赞成功";
            }
            if(1 == behavior.getIsLike()){
                return "重复点赞";
            }else{
                return "重复取消点赞";
            }
        } else {
            behaviorMapper.behaviorInsert(behavior);
            if(1 == behavior.getIsLike()){
                Article articleWrapper = new Article();
                articleWrapper.setId(behavior.getArticleId());
                Article article = articleMapper.getArticle(articleWrapper).get(0);
                article.setArticleLike(article.getArticleLike() + 1);
                articleMapper.updateArticle(article);
                return "点赞成功";
            }else{
                return "重复取消点赞";
            }
        }
    }
    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> queryComment(Long articleId){
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        List<Comment> comments = behaviorMapper.queryComment(comment);
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
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        List<Comment> list = behaviorMapper.queryComment(comment);
        if(null != list && list.size() > 0){
            Long userId = list.get(0).getUserId();
            if(Objects.equals(userId, BaseContext.getCurrentId())){
                behaviorMapper.delComment(comment);
            } else {
                throw new DeleteCommentException("只能删除自己的评论!");
            }
        }else{
            throw new NoSuchCommentException(NO_SUCH_COMMENT_ERROR);
        }
    }
}