package com.example.blog.controller;

import com.example.blog.Result;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 查询某篇文章的所有评论
    @GetMapping("/article/{articleId}")
    public Result<?> getCommentsByArticleId(@PathVariable Long articleId) {
        return commentService.getCommentsByArticleId(articleId);
    }

    // 发布评论
    @PostMapping
    public Result<String> createComment(@RequestBody Map<String, Object> params) {
        String content = (String) params.get("content");
        Long articleId = Long.valueOf(params.get("articleId").toString());
        Long userId = Long.valueOf(params.get("userId").toString());
        return commentService.createComment(content, articleId, userId);
    }

    // 删除评论
    @DeleteMapping("/{id}")
    public Result<String> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
