package com.example.demo.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author my_eyes
 */
@Controller
public class LoginController {

    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/")
    public String main() {
        return "/login";
    }

    @RequestMapping(value = "/work/userromms")
    public String userRooms(HttpServletResponse response) {

        return "/user/conferenceroom";
    }

    @RequestMapping(value = "/work/roomsofuser")
    public String roomsOfUser(HttpServletResponse response) {

        return "/user/roomsofuser";
    }

    @RequestMapping(value = "/work/userfeedback")
    public String userFeedback(HttpServletResponse response) {

        return "/user/feedback";
    }

    @RequestMapping(value = "/work/myrooms")
    public String myRooms(HttpServletRequest request, HttpSession session) {
        String temp = request.getParameter("roomId");
        session.setAttribute("roomId", temp);
        return "/user/myrooms";
    }

    @RequestMapping(value = "/work/usermessage")
    public String userMessage(HttpServletResponse response) {

        return "/user/usermessage";
    }

    @RequestMapping(value = "/work/exit")
    public String exit(HttpSession session) {

        session.setAttribute("token", "");
        session.setAttribute("role", "");

        return "/login";
    }

    @RequestMapping("/admin/login")
    public String adminLogin(HttpSession session) {

        return "/admin/rooms";
    }

    @RequestMapping("/admin/rooms")
    public String adminRooms(HttpSession session) {

        return "/admin/rooms";
    }

    @RequestMapping("/admin/user")
    public String adminUser(HttpSession session) {

        return "/admin/user";
    }

    @RequestMapping("/admin/message")
    public String adminMessage(HttpSession session) {

        return "/admin/message";
    }

    @RequestMapping("/admin/exit")
    public String adminExit(HttpSession session) {

        session.setAttribute("token", "");
        session.setAttribute("role", "");
        return "/login";
    }

    @RequestMapping("/admin/userroomsdate")
    public String adminTime(HttpSession session) {

        return "/admin/UserRoomsDate";
    }
}