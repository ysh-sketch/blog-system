package com.example.blog.service.impl;

import com.example.blog.entity.Article;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.service.ArticleService;
import com.example.blog.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Map<String, Object>> getArticlesByPage(int pageNum, int pageSize) {
        // 启动分页：pageNum=第几页，pageSize=每页几条
        PageHelper.startPage(pageNum, pageSize);

        // 查询数据（分页插件会自动在 SQL 后加 LIMIT）
        List<Article> articles = articleMapper.findAll();

        // 用 PageInfo 包装查询结果，获取分页信息
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        // 返回结果：文章列表 + 分页信息
        Map<String, Object> result = new HashMap<>();
        result.put("list", articles);
        result.put("total", pageInfo.getTotal());
        result.put("pageNum", pageInfo.getPageNum());
        result.put("pageSize", pageInfo.getPageSize());
        result.put("pages", pageInfo.getPages());

        return Result.success(result);
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
    public Result<String> createArticle(String title, String content, Long authorId) {
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

    @Override
    public Result<List<Article>> searchArticles(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Result.error("搜索关键字不能为空");
        }
        List<Article> articles = articleMapper.searchByKeyword(keyword.trim());
        return Result.success(articles);
    }
}
