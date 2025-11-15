package com.example.aptback.controller;

import com.example.aptback.entity.User;
import com.example.aptback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/selectAll")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public List<User> selectAll(){
        return userService.selectAll();
    }
}
