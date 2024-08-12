package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.pojo.Article;
import com.blog.pojo.SaveArticle;
import com.blog.pojo.SelectArticle;
import com.blog.pojo.ShowArticle;
import com.blog.properties.JwtProperties;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import com.blog.mapper.ArticleMapper;
import com.blog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public Result load() {
        List list = articleMapper.load();
        System.out.println(list);
        return Result.success(list);
    }

    @Override
    public Result selectById(Long id) {
        List list = articleMapper.selectByUserId(id);
        return Result.success(list);
    }

    @Override
    public Result saveArticle(SaveArticle saveArticle,String token) {
        //解析token得到userId
       Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
        Long userid  = (Long)claims.get("userId");
        Article article = new Article();
        article.setUserId(userid);
        article.setTitle(saveArticle.getTitle());
        article.setContent(saveArticle.getContent());
        article.setImage(saveArticle.getImage());
        article.setPublishTime(new Date());
        int i = articleMapper.saveArticle(article);
        return Result.success(null);
    }

    @Override
    public Result selectByArticleId(Long id) {
        SaveArticle saveArticle = articleMapper.selectByArticleId(id);
        return Result.success(saveArticle);
    }

    @Override
    public Result articleDetails(Long id) {
        ShowArticle showArticle = articleMapper.showArticle(id);
        Integer comments = articleMapper.countComment(id);
        showArticle.setCommentCount(comments);
        return Result.success(showArticle);
    }

    @Override
    public Result deleteArticle(Long id) {
        Integer i = articleMapper.deleteByArticleId(id);
        if(i<=0){
            return Result.error(405,"删除失败");
        }
        System.out.println(i);
        return Result.success(null);
    }

    @Override
    public Result updateArticle(SelectArticle selectArticle) {
        Article article = new Article();
        article.setArticleId(selectArticle.getId());
        article.setTitle(selectArticle.getTitle());
        article.setContent(selectArticle.getContent());
        article.setImage(selectArticle.getImage());
        article.setUpdateTime(new Date());
        int i = articleMapper.updateArticle(article);
        if(i<=0){
            return Result.error(405,"更改文章失败");
        }
        return Result.success(null);
    }
}




