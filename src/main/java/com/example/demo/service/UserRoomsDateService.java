package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserRoomsDate;
import com.example.demo.mapper.UserRoomsDateMapper;

@Service
public class UserRoomsDateService {
    @Autowired
    UserRoomsDateMapper userRoomsDateMapper;

    public List<UserRoomsDate> getAll(int userId) {
        return userRoomsDateMapper.getAll(userId);
    }

    public void delete(int timeId)
    {
        userRoomsDateMapper.delete(timeId);
    }
}
