package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Message;
import com.example.demo.domain.Rooms;
import com.example.demo.domain.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/insert")
public class InsertController {
    @Autowired
    RoomsService roomsService;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @RequestMapping("/rooms")
    public void insertRooms(@RequestBody Rooms rooms)
    {   
        roomsService.insert(rooms);
    }

    @RequestMapping("/user")
    public void insertUser(@RequestBody User user)
    {
        userService.insert(user);
    }

    @RequestMapping("/message")
    public void insertMessage(@RequestBody Message message,HttpSession session)
    {
        int userId = (int) session.getAttribute("userId");
        message.setUserId(userId);
        messageService.insert(message);
        System.out.println(message);
    }
}
