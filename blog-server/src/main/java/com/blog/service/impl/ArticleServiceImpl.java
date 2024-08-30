package com.blog.service.impl;

import com.blog.constant.MessageConstant;
import com.blog.context.BaseContext;
import com.blog.entity.Article;
import com.blog.entity.Comment;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.BehaviorMapper;
import com.blog.pojo.SaveArticle;
import com.blog.pojo.ShowArticle;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import com.blog.vo.SelectAllArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private BehaviorMapper behaviorMapper;
    @Override
    public Result load() {
        List list = articleMapper.load();
        System.out.println(list);
        return Result.success(list);
    }

    @Override
    public Result selectById(Long id) {
        List<Article> list = articleMapper.selectByUserId(id);
        List<SelectAllArticleVO> articles = new ArrayList();
        if(list != null && list.size() > 0){
            for(Article article : list) {
                SelectAllArticleVO selectAllArticle = new SelectAllArticleVO();
                BeanUtils.copyProperties(article,selectAllArticle);//这里当时我们的属性拷贝方法失效了，因为我们的SelectAllArticle实体类没有get/set方法，所以无效
                articles.add(selectAllArticle);
            }
            return Result.success(articles);
        }
        return Result.success("用户没有创作过文章");
    }

    @Override
    public Result saveArticle(Article saveArticle) {
        //从BaseContext中获取userId
        Long userId = BaseContext.getCurrentId();
        //测试使用userId
//        Long userid = 1L;
        saveArticle.setUserId(userId);//如果userId为null会在执行SQL语句时报错
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
        Article saveArticle = articleMapper.selectByArticleId(id);
        if (saveArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        return Result.success(saveArticle);
    }

    @Override
    public Result articleDetails(Long id) {
        Article showArticle = articleMapper.showArticle(id);
        if (showArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        Integer comments = articleMapper.countComment(id);
        showArticle.setCommentCount(comments);
        return Result.success(showArticle);
    }

    @Override
    @Transactional
    public Result deleteArticle(Long id) {
        //非用户本人不能删除
        Long userId = BaseContext.getCurrentId();//我们的Long类型赋值是调用valueOf方法来赋值的，可以赋值为null
        Comment comment = Comment.builder()
                .articleId(id)//
                .build();
        //测试使用userId
//        Long userId = 2L;
        Article showArticle = articleMapper.showArticle(id);
        if (showArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        long articlePublisherId = showArticle.getUserId();
        if (userId == articlePublisherId) {
            articleMapper.deleteByArticleId(id);
            behaviorMapper.delComment(comment);
            behaviorMapper.delBehaviorByArticleId(id);
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
        Article showArticle = articleMapper.showArticle(article.getId());
        if (showArticle == null) {
            return Result.error(50015, MessageConstant.NOSUSH_ARTICLE);
        }
        long articlePublisherId = showArticle.getUserId();
        if (userId != articlePublisherId) {
            return Result.error(50016, MessageConstant.RIGHT_ERROR);
        }
        int i = articleMapper.updateArticle(article);
        return Result.success(null);
    }
}




