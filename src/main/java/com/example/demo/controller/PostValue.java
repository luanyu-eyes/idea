package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostValue {

    @RequestMapping(value = "/code")
    public void delCode(String id, String codeNumber) {

        System.out.print("123");
        System.out.print(id);
        System.out.print(codeNumber);
    }
}
