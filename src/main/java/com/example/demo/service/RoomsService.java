package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Rooms;
import com.example.demo.mapper.RoomsMapper;

@Service
public class RoomsService {
    @Autowired
    RoomsMapper roomsMapper;

    public Rooms getAll(int roomsId) {
        return roomsMapper.getAll(roomsId);
    }

    public boolean delete(int roomId) {
        return roomsMapper.delete(roomId);
    }

    public void insert(Rooms rooms) {
        roomsMapper.insert(rooms);
    }

    public void update(Rooms rooms){
        if(rooms.getRoomsType()=="小型会议室"||rooms.getRoomsType()=="中型会议室"||rooms.getRoomsType()=="大型会议室"||rooms.getRoomsType()=="礼堂")

        roomsMapper.update(rooms);
        else{
            throw new RuntimeException("数据类型错误");
        }
    }

    public void insertAll(List<Rooms> list){
        
    }
}
