package com.blog.service;
import com.blog.entity.Article;
import com.blog.result.Result;

import java.util.List;

/**
* @author 86155
* @description 针对表【article(文章)】的数据库操作Service
* @createDate 2024-08-09 11:05:18
*/
public interface ArticleService{

    Result load();

    Result selectById(Long id);

    Result saveArticle(com.blog.entity.Article article);

    Result selectByArticleId(Long id);

    Result articleDetails(Long id);

    Result deleteArticle(Long id);

    Result updateArticle(com.blog.entity.Article article);
}
