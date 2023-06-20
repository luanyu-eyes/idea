package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;

/**
 * @author my_eyes
 */
@Service
public interface LoginUserService {

    User login(String userId);
}
