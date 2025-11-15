package com.example.aptback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aptback.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    //查所有用户
    // 新增
    void add(User user);

    // 删除
    void deleteById(Integer id);

    // 更新
    void update(User user);

    // 查询单个
    User selectById(Integer id);

    // 查询所有
    List<User> selectAll();
}
