package com.example.blog.service.impl;

import com.example.blog.entity.Tag;
import com.example.blog.mapper.TagMapper;
import com.example.blog.service.TagService;
import com.example.blog.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Result<List<Tag>> getAllTags() {
        List<Tag> tags = tagMapper.findAll();
        return Result.success(tags);
    }

    @Override
    public Result<String> createTag(String name) {
        // 检查标签是否已存在
        Tag existing = tagMapper.findByName(name);
        if (existing != null) {
            return Result.error("标签已存在");
        }

        Tag tag = new Tag();
        tag.setName(name);
        tagMapper.insert(tag);
        return Result.success("标签创建成功，ID：" + tag.getId());
    }

    @Override
    public Result<String> deleteTag(Long id) {
        int rows = tagMapper.deleteById(id);
        if (rows > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败，标签不存在");
        }
    }
}