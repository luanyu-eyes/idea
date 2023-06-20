package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Sel(int id) {
        return userMapper.Sel(id);
    }

    public User Sel1(String username) {
        return userMapper.Sel1(username);
    }

    public void insert(User user) {
        if(Sel1(user.getUserName()) == null)
        userMapper.insert(user);
        else
        {
            throw new RuntimeException("用户名重复");
        }
    }

    public void delete(int id)
    {
        userMapper.delete(id);
    }

    public void update(User user)
    {
        System.out.println(user.getId());
        userMapper.update(user);
    }
}