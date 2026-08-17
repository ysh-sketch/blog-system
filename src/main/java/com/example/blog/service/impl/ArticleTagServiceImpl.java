package com.example.blog.service.impl;

import com.example.blog.entity.ArticleTag;
import com.example.blog.entity.Tag;
import com.example.blog.mapper.ArticleTagMapper;
import com.example.blog.mapper.TagMapper;
import com.example.blog.service.ArticleTagService;
import com.example.blog.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Result<List<Tag>> getTagsByArticleId(Long articleId) {
        // 先查询文章-标签关联
        List<ArticleTag> articleTags = articleTagMapper.findByArticleId(articleId);

        // 再根据 tagId 查询标签详情
        List<Tag> tags = new ArrayList<>();
        for (ArticleTag at : articleTags) {
            Tag tag = tagMapper.findById(at.getTagId());
            if (tag != null) {
                tags.add(tag);
            }
        }

        return Result.success(tags);
    }

    @Override
    public Result<String> addTagToArticle(Long articleId, Long tagId) {
        if (articleTagMapper.countByArticleAndTag(articleId, tagId) > 0) {
            return Result.error("该标签已存在");
        }
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(articleId);
        articleTag.setTagId(tagId);
        articleTagMapper.insert(articleTag);
        return Result.success("添加标签成功");
    }

    @Override
    public Result<String> removeTagFromArticle(Long articleId, Long tagId) {
        int rows = articleTagMapper.delete(articleId, tagId);
        if (rows > 0) {
            return Result.success("删除标签成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
