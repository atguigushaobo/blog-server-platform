package com.blog.controller;

import com.blog.entity.Article;
import com.blog.pojo.SelectArticle;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    /**
     * 查询所有文章
     * @return
     */
    @GetMapping("load")
    public Result load(){
        return articleService.load();
    }
    @GetMapping("selectByUserId/{id}")
    public Result selectByUserId(@PathVariable("id") Long id){
        return articleService.selectById(id);
    }
    @PostMapping("saveArticle")
    public Result saveArticle(@RequestBody Article saveArticle){
        return articleService.saveArticle(saveArticle);
    }
    @GetMapping("selectByArticleId/{id}")
    public Result selectByArticleId(@PathVariable("id") Long id){
        return articleService.selectByArticleId(id);
    }
    @GetMapping("articleDetails/{id}")
    public Result articleDetails(@PathVariable("id") Long id){
        return articleService.articleDetails(id);
    }
    @DeleteMapping("deleteArticle/{id}")
    public Result deleteArticle(@PathVariable("id") Long id){
        return articleService.deleteArticle(id);
    }
    @PutMapping("updateArticle")
    public Result updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

}
