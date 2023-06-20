package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Admin;

@Repository
public interface AdminMapper {
    Admin getAll(int adminId);
    Admin getAll1(String adminName);
}
