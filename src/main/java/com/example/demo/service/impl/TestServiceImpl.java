package com.example.demo.service.impl;

import com.example.demo.service.TestService;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Test;

/**
 * @author my_eyes
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public Test<String> rvalue() {
        Test<String> test = new Test<>();

        return test;
    }
}
