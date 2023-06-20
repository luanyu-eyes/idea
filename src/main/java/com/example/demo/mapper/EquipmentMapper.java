package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

/**
 * @author my_eyes
 */

import com.example.demo.domain.Equipment;

@Repository
public interface EquipmentMapper {
    Equipment getAll(int equipmentId);
}
