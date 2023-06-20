package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Message;
import com.example.demo.mapper.MessageMapper;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public List<Message> getAll(int userId) {
        return messageMapper.getAll(userId);
    }

    public void delete(int messageId)
    {
        messageMapper.delete(messageId);
    }

    public void update(Message message)
    {
        messageMapper.update(message);
    }

    public void insert(Message message)
    {
        messageMapper.insert(message);
    }
}
