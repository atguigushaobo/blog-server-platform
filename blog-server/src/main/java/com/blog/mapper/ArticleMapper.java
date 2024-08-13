package com.blog.mapper;

import com.blog.entity.Article;

/**
 * ClassName: ArticleMapper
 * Package: com.blog.mapper
 * Description:
 *
 * @Author :triumph
 * @Create 2024/8/12 下午1:44
 * @Version 1.0
 */
public interface ArticleMapper {
    int update(Article article);
    Article queryArticleById(Long id);
}
