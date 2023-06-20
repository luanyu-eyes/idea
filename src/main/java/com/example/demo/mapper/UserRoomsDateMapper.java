package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.UserRoomsDate;

/**
 * @author my_eyes
 */

@Repository
public interface UserRoomsDateMapper {
    List<UserRoomsDate> getAll(int userId);

    void delete (int timeId);
}
