package com.example.blog.mapper;

import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("SELECT id, title, content, author_id, created_at, updated_at FROM articles ORDER BY created_at DESC")
    List<Article>findAll();

    @Select("SELECT id, title, content, author_id, created_at, updated_at FROM articles WHERE id=#{id}")
    Article findById(Long id);

    @Insert("INSERT INTO articles (title, content, author_id) VALUES (#{title},#{content},#{authorId})")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Article article);

    @Delete("DELETE FROM articles WHERE id = #{id}")
    int deleteById(Long id);
}
