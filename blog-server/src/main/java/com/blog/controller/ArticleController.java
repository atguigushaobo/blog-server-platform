package com.blog.controller;

import com.blog.pojo.SaveArticle;
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
        Result result =  articleService.load();
        return result;
    }
    @GetMapping("selectByUserId/{id}")
    public Result selectByUserId(@PathVariable Long id){
        Result result = articleService.selectById(id);
        return result;
    }
    @PostMapping("saveArticle")
    public Result saveArticle(@RequestBody SaveArticle saveArticle,@RequestHeader String token){
        Result result = articleService.saveArticle(saveArticle,token);
        return  result;
    }
    @GetMapping("selectByArticleId/{id}")
    public Result selectByArticleId(@PathVariable Long id){
        Result result = articleService.selectByArticleId(id);
        return result;
    }
    @GetMapping("articleDetails/{id}")
    public Result articleDetails(@PathVariable Long id){
        Result result = articleService.articleDetails(id);
        return result;
    }
    @DeleteMapping("deleteArticle/{id}")
    public Result deleteArticle(@PathVariable Long id){
        Result result = articleService.deleteArticle(id);
        return result;
    }
    @PutMapping("updateArticle")
    public Result updateArticle(@RequestBody SelectArticle selectArticle){
        Result result = articleService.updateArticle(selectArticle);
        return result;
    }

}
