package com.blog.service.impl;

import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.blog.service.impl
 * Description:
 *
 * @Author :triumph
 * @Create 2024/8/12 下午1:43
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Result update(Article article) {
        if (article.getId() == null) {
            return Result.error(50010, "没有指定修改文章的Id");
        }
        articleMapper.update(article);
        return Result.success(null);
    }
}
