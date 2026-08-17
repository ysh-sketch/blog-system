package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.Result;
import java.util.List;

public interface CommentService {

    // 查询某篇文章的所有评论
    Result<List<Comment>> getCommentsByArticleId(Long articleId);

    // 发布评论
    Result<String> createComment(String content, Long articleId, Long userId);

    // 删除评论
    Result<String> deleteComment(Long id);
}