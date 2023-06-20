package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Rooms;
import com.example.demo.domain.User;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/update")
public class UpdateController {
    @Autowired
    RoomsService roomsService;

    @Autowired
    UserService userService;

    @RequestMapping("/rooms")
    public void updateRooms(@RequestBody Rooms rooms){
        roomsService.update(rooms);
    }

    @RequestMapping("/user")
    public void update(@RequestBody User user)
    {
        userService.update(user);;
    }
}
