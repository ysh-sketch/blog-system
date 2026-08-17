package com.example.blog.service.impl;

import com.example.blog.entity.Comment;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.service.CommentService;
import com.example.blog.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result<List<Comment>> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentMapper.findByArticleId(articleId);
        return Result.success(comments);
    }

    @Override
    public Result<String> createComment(String content, Long articleId, Long userId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setArticleId(articleId);
        comment.setUserId(userId);
        commentMapper.insert(comment);
        return Result.success("评论发布成功，ID：" + comment.getId());
    }

    @Override
    public Result<String> deleteComment(Long id) {
        int rows = commentMapper.deleteById(id);
        if (rows > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败，评论不存在");
        }
    }
}