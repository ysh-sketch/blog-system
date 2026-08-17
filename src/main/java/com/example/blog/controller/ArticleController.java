package com.example.blog.controller;

import com.example.blog.entity.Article;
import com.example.blog.Result;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 查看文章列表
    @GetMapping
    public Result<java.util.List<Article>> getAllArticles() {
        return articleService.getAllArticles();
    }

    // 分页查询文章列表
    @GetMapping("/page")
    public Result<Map<String, Object>> getArticlesByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return articleService.getArticlesByPage(pageNum, pageSize);
    }

    // 查看文章详情
    @GetMapping("/{id}")
    public Result<Article> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    // 发布文章
    @PostMapping
    public Result<String> createArticle(@RequestBody Map<String, Object> params) {
        String title = (String) params.get("title");
        String content = (String) params.get("content");
        Long authorId = Long.valueOf(params.get("authorId").toString());
        return articleService.createArticle(title, content, authorId);
    }

    // 删除文章
    @DeleteMapping("/{id}")
    public Result<String> deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }
}