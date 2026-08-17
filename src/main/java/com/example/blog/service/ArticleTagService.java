package com.example.blog.service;

import com.example.blog.entity.Tag;
import com.example.blog.Result;
import java.util.List;

public interface ArticleTagService {

    // 获取文章的所有标签
    Result<List<Tag>> getTagsByArticleId(Long articleId);

    // 给文章添加标签
    Result<String> addTagToArticle(Long articleId, Long tagId);

    // 删除文章的标签
    Result<String> removeTagFromArticle(Long articleId, Long tagId);
}