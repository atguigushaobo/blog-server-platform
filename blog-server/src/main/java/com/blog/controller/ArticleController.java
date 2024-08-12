package com.blog.controller;

import com.blog.entity.Article;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PutMapping("/updateArticle")
    public Result update(@RequestBody Article article) {
        Result result = articleService.update(article);
        return result;
    }

}
