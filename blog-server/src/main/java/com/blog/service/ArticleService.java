package com.blog.service;

import com.blog.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.SaveArticle;
import com.blog.pojo.SelectArticle;
import com.blog.result.Result;

/**
* @author 86155
* @description 针对表【article(文章)】的数据库操作Service
* @createDate 2024-08-09 11:05:18
*/
public interface ArticleService extends IService<Article> {

    Result load();

    Result selectById(Long id);

    Result saveArticle(com.blog.entity.Article article);

    Result selectByArticleId(Long id);

    Result articleDetails(Long id);

    Result deleteArticle(Long id);

    Result updateArticle(com.blog.entity.Article article);

}
