package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Message;

/**
 * @author my_eyes
 */

@Repository
public interface MessageMapper {
    List<Message> getAll(int userId);

    void delete(int messageId);

    void update(Message message);

    void insert(Message message);
}
