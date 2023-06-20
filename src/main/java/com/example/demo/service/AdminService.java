package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Admin;
import com.example.demo.mapper.AdminMapper;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public Admin getAll(int adminId) {
        return adminMapper.getAll(adminId);
    }

    public Admin getAll1(String adminname)
    {
        return adminMapper.getAll1(adminname);
    }
}
