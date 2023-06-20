package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author my_eyes
 */
@Controller
@RequestMapping(value = "/book")
public class BookRoomController {

    @RequestMapping(value = "/room")
    public String book() {
        return "user/conferenceroom";
    }
}