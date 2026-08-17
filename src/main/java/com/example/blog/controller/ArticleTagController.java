package com.example.blog.controller;

import com.example.blog.Result;
import com.example.blog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/article-tags")
public class ArticleTagController {

    @Autowired
    private ArticleTagService articleTagService;

    // 获取文章的所有标签
    @GetMapping("/article/{articleId}")
    public Result<?> getTagsByArticleId(@PathVariable Long articleId) {
        return articleTagService.getTagsByArticleId(articleId);
    }

    // 给文章添加标签
    @PostMapping
    public Result<String> addTagToArticle(@RequestBody Map<String, Object> params) {
        Long articleId = Long.valueOf(params.get("articleId").toString());
        Long tagId = Long.valueOf(params.get("tagId").toString());
        return articleTagService.addTagToArticle(articleId, tagId);
    }

    // 删除文章的标签
    @DeleteMapping
    public Result<String> removeTagFromArticle(@RequestBody Map<String, Object> params) {
        Long articleId = Long.valueOf(params.get("articleId").toString());
        Long tagId = Long.valueOf(params.get("tagId").toString());
        return articleTagService.removeTagFromArticle(articleId, tagId);
    }
}