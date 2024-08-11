package com.blog.controller;

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



}
