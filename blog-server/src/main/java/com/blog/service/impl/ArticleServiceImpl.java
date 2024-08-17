package com.blog.service.impl;

import com.blog.constant.MessageConstant;
import com.blog.context.BaseContext;
import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.pojo.SaveArticle;
import com.blog.pojo.ShowArticle;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 86155
* @description 针对表【article(文章)】的数据库操作Service实现
* @createDate 2024-08-09 11:05:18
*/
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Result load() {
        List list = articleMapper.load();
        System.out.println(list);
        return Result.success(list);
    }

    @Override
    public Result selectById(Long id) {
        long userId = BaseContext.getCurrentId();
        if (userId ==  id) {
            List list = articleMapper.selectByUserId(id);
            return Result.success(list);
        }
        return Result.error(50014, MessageConstant.RIGHT_ERROR);
    }

    @Override
    public Result saveArticle(com.blog.entity.Article saveArticle) {
        //从BaseContext中获取userId
        Long userid = BaseContext.getCurrentId();
        //测试使用userId
//        Long userid = 1L;
        saveArticle.setUserId(userid);
        saveArticle.setPublishTime(new Date());
        saveArticle.setUpdateTime(new Date());
        int i = articleMapper.saveArticle(saveArticle);
        if (i > 0) {
            return Result.success(null);
        }
        return Result.error(50013, MessageConstant.UNKNOWERROR);
    }

    @Override
    public Result selectByArticleId(Long id) {
        SaveArticle saveArticle = articleMapper.selectByArticleId(id);
        if (saveArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        return Result.success(saveArticle);
    }

    @Override
    public Result articleDetails(Long id) {
        ShowArticle showArticle = articleMapper.showArticle(id);
        if (showArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        Integer comments = articleMapper.countComment(id);
        showArticle.setCommentCount(comments);
        return Result.success(showArticle);
    }

    @Override
    public Result deleteArticle(Long id) {
        //非用户本人不能删除
        Long userId = BaseContext.getCurrentId();
        //测试使用userId
//        Long userId = 2L;
        ShowArticle showArticle = articleMapper.showArticle(id);
        if (showArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        long articlePublisherId = showArticle.getUserId();
        if (userId == articlePublisherId) {
            Integer i = articleMapper.deleteByArticleId(id);
            if(i<=0){
                return Result.error(50016,MessageConstant.UNKNOWERROR);
            }
            return Result.success(null);
        }
        return Result.error(50015, MessageConstant.RIGHT_ERROR);
    }

    @Override
    public Result updateArticle(Article article) {
        //判断文章的发布者Id和当前Id是否相同
        Long userId = BaseContext.getCurrentId();
        //测试Id
//        long userId = 2L;
        ShowArticle showArticle = articleMapper.showArticle(article.getId());
        if (showArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        long articlePublisherId = showArticle.getUserId();
        if (userId != articlePublisherId) {
            return Result.error(50016, MessageConstant.RIGHT_ERROR);
        }
        article.setUpdateTime(new Date());
        int i = articleMapper.updateArticle(article);
        return Result.success(null);
    }
}




