package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Equipment;
import com.example.demo.mapper.EquipmentMapper;

@Service
public class EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;

    public Equipment getAll(int roomsId) {
        return equipmentMapper.getAll(roomsId);
    }
}
