package com.example.aptback;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.aptback.entity.User;
import com.example.aptback.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AptbackApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询全部数据
        List<User> all = userMapper.selectAll();
        System.out.println(all);

        // 根据ID查询单个数据
        User user = userMapper.selectById(1);
        System.out.println(user);

        // 删除数据
        userMapper.deleteById(3);
    }

    @Test
    void update() {
        // 更新数据
        User user2 = new User();
        user2.setId(2);
        user2.setUserName("霜霜");
        user2.setPassword("123");
        user2.setRole("user");
        userMapper.update(user2);
    }

    @Test
    public void mybatisPlus(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name","admin");

        LambdaQueryWrapper<User> queryWrapper1 =new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getUserName,"admin");
        List<User> users= userMapper.selectList(queryWrapper1);
    }
}
