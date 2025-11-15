package com.example.aptback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aptback.entity.User;
import com.example.aptback.exception.ServiceException;
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


    @Override
    public List<User> selectSearch(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((userName != "" && userName!=null),User::getUserName,userName);
        List<User> list = userMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public IPage<User> selectPage(Integer pageNum, Integer pageSize, String userName) {
        Page<User> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        Page<User> page1 = userMapper.selectPage(page,queryWrapper);
        return page1;
    }

    @Override
    public void insert(User user) {
        if (null == user.getUserName() || user.getUserName().equals("")){
            throw new ServiceException("用户名不能为空");
        }
        //唯一校验
        LambdaQueryWrapper<User> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        User one = userMapper.selectOne(queryWrapper);
        if (null != one){
            throw new ServiceException("用户名已存在");
        }
        if (null == user.getPassword()){
            user.setPassword("123456");
        }
        if (null == user.getRole()){
            user.setRole("用户");
        }
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        if (null == user.getUserName() || user.getUserName().equals("")){
            throw new ServiceException("用户名不能为空");
        }
        //唯一校验
        LambdaQueryWrapper<User> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        User one = userMapper.selectOne(queryWrapper);
        if (null != one){
            throw new ServiceException("用户名已存在");
        }
        if (null == user.getPassword()){
            user.setPassword("123456");
        }
        if (null == user.getRole()){
            user.setRole("用户");
        }
        userMapper.updateById(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }
    @Override
    public User login(User user){
        if (null == user.getUserName() || user.getUserName().equals("")){
            throw new ServiceException("用户名不能为空");
        }
        if (null == user.getPassword() || user.getPassword().equals("")){
            throw new ServiceException("密码不能为空");
        }
        //唯一校验
        LambdaQueryWrapper<User> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        queryWrapper.eq(User::getPassword,user.getPassword());
        User one = userMapper.selectOne(queryWrapper);
        if (null == one){
            throw new ServiceException("用户名或密码错误");
        }
        return one;
    }
}
