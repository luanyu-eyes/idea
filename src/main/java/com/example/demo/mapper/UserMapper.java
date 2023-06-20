package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * @author my_eyes
 */

@Repository
public interface UserMapper {
    User Sel(int id);

    User Sel1(String username);

    void delete(int id);

    void insert(User user);

    void update(User user);


}
