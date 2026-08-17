package com.example.blog.controller;

import com.example.blog.Result;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // 获取所有标签
    @GetMapping
    public Result<?> getAllTags() {
        return tagService.getAllTags();
    }

    // 创建标签
    @PostMapping
    public Result<String> createTag(@RequestBody Map<String, Object> params) {
        String name = (String) params.get("name");
        return tagService.createTag(name);
    }

    // 删除标签
    @DeleteMapping("/{id}")
    public Result<String> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}
