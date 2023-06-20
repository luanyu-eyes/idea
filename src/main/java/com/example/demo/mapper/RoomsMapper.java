package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Rooms;

/**
 * @author my_eyes
 */

@Repository
public interface RoomsMapper {
    Rooms getAll(int roomsId);

    boolean delete(int roomsId);

    void insert(Rooms rooms);

    void update(Rooms rooms);
}
