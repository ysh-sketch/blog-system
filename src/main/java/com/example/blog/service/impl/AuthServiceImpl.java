package com.example.blog.service.impl;

import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.AuthService;
import com.example.blog.Result;
import com.example.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> login(String username, String password) {
        // 根据用户名查询用户
        User user = userMapper.findByUsername(username);

        // 用户不存在
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 验证密码（暂时明文比较，后面会加密）
        if (!user.getPassword().equals(password)) {
            return Result.error("密码错误");
        }

        // 生成 JWT Token
        String token = JwtUtil.generateToken(username, user.getId());

        return Result.success(token);
    }

    @Override
    public Result<String> register(String username, String password, String email) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(username);
        if (existingUser != null) {
            return Result.error("用户名已存在");
        }

        // 创建新用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // 暂时明文存储，后面会加密
        user.setEmail(email);
        userMapper.insert(user);

        return Result.success("注册成功");
    }
}