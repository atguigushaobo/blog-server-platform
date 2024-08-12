package com.blog.service;

import com.blog.entity.Article;
import com.blog.result.Result;

/**
 * ClassName: ArticleService
 * Package: com.blog.service
 * Description:
 *
 * @Author :triumph
 * @Create 2024/8/12 下午1:43
 * @Version 1.0
 */
public interface ArticleService {
    Result update(Article article);
}
