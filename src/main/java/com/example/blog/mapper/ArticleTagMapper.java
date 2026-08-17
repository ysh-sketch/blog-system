package com.example.blog.mapper;

import com.example.blog.entity.ArticleTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleTagMapper {

    // 查询某篇文章的所有标签
    @Select("SELECT article_id, tag_id FROM article_tags WHERE article_id = #{articleId}")
    List<ArticleTag> findByArticleId(Long articleId);

    // 给文章添加标签
    @Insert("INSERT INTO article_tags (article_id, tag_id) VALUES (#{articleId}, #{tagId})")
    void insert(ArticleTag articleTag);

    // 删除文章的某个标签
    @Delete("DELETE FROM article_tags WHERE article_id = #{articleId} AND tag_id = #{tagId}")
    int delete(@Param("articleId") Long articleId, @Param("tagId") Long tagId);

    // 删除文章的所有标签
    @Delete("DELETE FROM article_tags WHERE article_id = #{articleId}")
    int deleteByArticleId(Long articleId);

    @Select("SELECT COUNT(*) FROM article_tags WHERE article_id = #{articleId} AND tag_id = #{tagId}")
    int countByArticleAndTag(@Param("articleId") Long articleId, @Param("tagId") Long tagId);
}
