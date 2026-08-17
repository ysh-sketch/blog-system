package com.example.blog.service;

import com.example.blog.entity.Tag;
import com.example.blog.Result;
import java.util.List;

public interface TagService {

    // 获取所有标签
    Result<List<Tag>> getAllTags();

    // 创建标签
    Result<String> createTag(String name);

    // 删除标签
    Result<String> deleteTag(Long id);
}