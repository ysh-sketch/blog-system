package com.example.blog.mapper;

import com.example.blog.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, password, email, created_at FROM users WHERE username =#{username}")
    User findByUsername(String username);

    @Select("SELECT id, username, password, email, created_at FROM users WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password},#{email})")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);
}
