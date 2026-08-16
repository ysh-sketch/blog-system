package com.example.blog.service.impl;

import com.example.blog.entity.Article;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.service.ArticleService;
import com.example.blog.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Result<List<Article>> getAllArticles() {
        List<Article> articles = articleMapper.findAll();
        return Result.success(articles);
    }

    @Override
    public Result<Article> getArticleById(Long id) {
        Article article = articleMapper.findById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        return Result.success(article);
    }

    @Override
      public Result<String> createArticle(String title, String content, Long authorId)
  {
          Article article = new Article();
          article.setTitle(title);
          article.setContent(content);
          article.setAuthorId(authorId);
          articleMapper.insert(article);
          return Result.success("文章发布成功，ID：" + article.getId());
      }

    @Override
      public Result<String> deleteArticle(Long id) {
          int rows = articleMapper.deleteById(id);
          if (rows > 0) {
              return Result.success("删除成功");
          } else {
              return Result.error("删除失败，文章不存在");
          }
      }
}
