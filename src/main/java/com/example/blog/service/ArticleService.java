package com.example.blog.service;

import com.example.blog.entity.Article;
import com.example.blog.Result;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    Result<List<Article>> getAllArticles();

    Result<Map<String, Object>> getArticlesByPage(int pageNum, int pageSize);

    Result<Article> getArticleById(Long id);

    Result<String> createArticle(String title, String content, Long authorId);

    Result<String> deleteArticle(Long id);
}