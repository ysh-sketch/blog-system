package com.example.blog.mapper;

import com.example.blog.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    // 查询所有标签
    @Select("SELECT id, name FROM tags")
    List<Tag> findAll();

    // 根据 ID 查询标签
    @Select("SELECT id, name FROM tags WHERE id = #{id}")
    Tag findById(Long id);

    // 根据名称查询标签
    @Select("SELECT id, name FROM tags WHERE name = #{name}")
    Tag findByName(String name);

    // 插入标签
    @Insert("INSERT INTO tags (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Tag tag);

    // 删除标签
    @Delete("DELETE FROM tags WHERE id = #{id}")
    int deleteById(Long id);
}