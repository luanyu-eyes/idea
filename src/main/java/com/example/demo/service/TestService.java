package com.example.demo.service;

import com.example.demo.domain.Test;

/**
 * @author my_eyes
 */

public interface TestService {
    /**
     * 获取全部数据(JSON格式)
     * 
     * @return
     */
    public Test<String> rvalue();
}
