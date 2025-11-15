package com.example.aptback.controller;

import com.example.aptback.common.Result;
import com.example.aptback.entity.User;
import com.example.aptback.service.IUserService;
import jakarta.servlet.annotation.HttpConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(userService.selectAll());
    }
    @GetMapping("/selectSearch")
    public Result selectSearch(@RequestParam String userName){
        return Result.success(userService.selectSearch(userName));
    }
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam String userName){
        return Result.success(userService.selectPage(pageNum,pageSize,userName));
    }
    @PostMapping
    public Result insert(@RequestBody User user){
        if (null == user.getId()) {
            userService.insert(user);
            return Result.success();
        }else{
            userService.update(user);
            return Result.success();
        }
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        userService.delete(id);
        return Result.success();
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User one = userService.login(user);
        return Result.success(one);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.insert(user);
        return Result.success();
    }
}
