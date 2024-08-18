package com.blog.controller;
import com.blog.entity.*;
import com.blog.entity.Behavior;
import com.blog.result.Result;
import com.blog.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户行为的模块
 */
@RestController
@RequestMapping("/behaviour")
public class BehaviourController {
    @Autowired
    private BehaviorService behaviorService;
    @PostMapping("/likeOperate")
    public Result likeOperate(@RequestBody Behavior behavior){
        return Result.success(behaviorService.likeOperate(behavior));
    }
    @GetMapping("/listComment/{articleId}")
    @ResponseBody
    public Result queryComment(@PathVariable Long articleId){
        List<Comment> comments = behaviorService.queryComment(articleId);
        return Result.success(comments);
    }
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment){
        behaviorService.addComment(comment);
        return Result.success(null);
    }
    @DeleteMapping("/deleteComment/{commentId}")
    public Result delComment(@PathVariable Long commentId){
        behaviorService.delComment(commentId);
        return Result.success(null);
    }
}
