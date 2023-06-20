package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Message;
import com.example.demo.domain.Rooms;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRoomsDate;

@Service
public class JdbcService {

    // 注意，这里一定要用注解的形式，这样spring才能读取到配置文件的信息
    // 不能使用 new JdbcTemplate()的形式
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rooms> getAllRooms() {
        String sql = "select * from rooms";
        List<Rooms> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rooms.class));

        return list;
    }

    public List<User> getAllUser() {
        String sql = "select * from user";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        return list;
    }

    public List<Message> getAllMessage() {
        String sql = "select * from message";
        List<Message> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class));
        return list;
    }

    public List<UserRoomsDate> getAllTime() {
        String sql = "select * from userroomsdate";
        List<UserRoomsDate> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserRoomsDate.class));

        return list;
    }
}
