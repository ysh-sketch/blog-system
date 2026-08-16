package com.example.blog.service;

import com.example.blog.Result;

public interface AuthService {

    Result<String> login(String username, String password);

    Result<String> register(String username, String password, String email);
}