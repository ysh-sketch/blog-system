package com.example.blog.entity;

public class ArticleTag {
    private Long articleId;
    private Long tagId;

    // Getter 和 Setter
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}