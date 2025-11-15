package com.example.aptback.service.impl;

import com.example.aptback.entity.User;
import com.example.aptback.mapper.UserMapper;
import com.example.aptback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.selectList(null);
    }
}
