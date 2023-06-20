package com.example.demo.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Equipment;
import com.example.demo.domain.Message;
import com.example.demo.domain.Rooms;
import com.example.demo.domain.Test;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRoomsDate;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UserRoomsDateService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

import com.example.demo.service.EquipmentService;
import com.example.demo.service.JdbcService;
import com.example.demo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserRoomsDateService userRoomsDateService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    JdbcService jdbcService;

    @Autowired
    RoomsService roomsService;

    @Autowired
    private UserService userService;

    @RequestMapping("getUser")
    public String GetUser(int id) {

        return userService.Sel(id).toString();
    }

    @RequestMapping("getAllTime")
    public Test<UserRoomsDate> getAllTime() {

        Test<UserRoomsDate> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        test.setData(jdbcService.getAllTime());
        List<UserRoomsDate> list = jdbcService.getAllTime();
        System.out.println(list.get(0));
        Time time = list.get(0).getStartTime();
        System.out.println(time);
        String aa="12:12:102";
        Time time1 = Time.valueOf(aa);
        System.out.println(time1);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println(format);
        return test;
    }

    @RequestMapping("getAllUser")
    public Test<User> getAllUser() {

        Test<User> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        test.setData(jdbcService.getAllUser());
        return test;
    }

    @RequestMapping("getAllRooms")
    public Test<Rooms> getAllRooms() {
        Test<Rooms> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        test.setData(jdbcService.getAllRooms());
        return test;
    }

    @RequestMapping("getAllMessage")
    public Test<Message> getAllMessage() {
        Test<Message> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        test.setData(jdbcService.getAllMessage());
        return test;
    }

    @RequestMapping("getrooms")
    public Test<Rooms> getRooms(int roomId) {

        Test<Rooms> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        Rooms rooms = roomsService.getAll(roomId);
        List<Rooms> list = new ArrayList<>();
        list.add(rooms);
        test.setData(list);

        return test;
    }

    @RequestMapping("getequipment")
    public Test<Equipment> getEquipment(int roomId) {
        Test<Equipment> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        Equipment equipment = equipmentService.getAll(roomId);
        List<Equipment> list = new ArrayList<>();
        list.add(equipment);
        test.setData(list);

        return test;
    }

    @RequestMapping("getdbtime")
    public Test<UserRoomsDate> getdbTime(int userId) {
        Test<UserRoomsDate> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        List<UserRoomsDate> list = userRoomsDateService.getAll(userId);
        test.setData(list);

        return test;
    }

    @RequestMapping("getval")
    public Test<String> getVal(HttpSession session) {
        Test<String> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        String temp = session.getAttribute("roomId").toString();
        List<String> list = new ArrayList<>();
        list.add(temp);
        test.setData(list);

        return test;
    }

    @RequestMapping("getmessage")
    public Test<Message> getMessage(int userId) {
        Test<Message> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        List<Message> list = messageService.getAll(userId);
        test.setData(list);

        return test;
    }

    @RequestMapping("getuserid")
    public String getUserId(HttpSession session) {
        return session.getAttribute("userId").toString();
    }

    @RequestMapping("getuserbyid")
    public Test<User> getUserById(HttpSession session){
        int id = (int) session.getAttribute("userId");

        User user = userService.Sel(id);
        Test<User> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        List<User> list = new ArrayList<>();
        list.add(user);
        test.setData(list);

        return test;
    }
}