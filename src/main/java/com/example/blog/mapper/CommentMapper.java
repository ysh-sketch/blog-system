package com.example.blog.mapper;

import com.example.blog.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 查询某篇文章的所有评论
    @Select("SELECT id, content, article_id, user_id, created_at FROM comments WHERE article_id = #{articleId} ORDER BY created_at DESC")
    List<Comment> findByArticleId(Long articleId);

    // 根据 ID 查询评论
    @Select("SELECT id, content, article_id, user_id, created_at FROM comments WHERE id = #{id}")
    Comment findById(Long id);

    // 插入评论
    @Insert("INSERT INTO comments (content, article_id, user_id) VALUES (#{content}, #{articleId}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Comment comment);

    // 删除评论
    @Delete("DELETE FROM comments WHERE id = #{id}")
    int deleteById(Long id);
}
