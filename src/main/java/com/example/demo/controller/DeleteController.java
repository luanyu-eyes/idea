package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.MessageService;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UserRoomsDateService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    RoomsService roomsService;

    @Autowired 
    UserService userService;

    @Autowired 
    MessageService messageService;

    @Autowired 
    UserRoomsDateService userRoomsDateService;

    @RequestMapping("/rooms")
    public void deleteRooms(int roomsId) {
        roomsService.delete(roomsId);
    }

    @RequestMapping("/user")
    public void deleteUser(int id){
        userService.delete(id);
    }

    @RequestMapping("/message")
    public void deleteMessage(int messageId)
    {
        messageService.delete(messageId);
    }

    @RequestMapping("/time")
    public void deletTime(int timeId)
    {
        userRoomsDateService.delete(timeId);
    }

}
