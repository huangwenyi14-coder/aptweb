package com.example.aptback.service;

import com.example.aptback.entity.User;

import java.util.List;

public interface IUserService {
    List<User> selectAll();
}
