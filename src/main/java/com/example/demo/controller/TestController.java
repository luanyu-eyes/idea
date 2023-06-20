package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.CityMessage;
import com.example.demo.domain.Login;
import com.example.demo.domain.Test;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author my_eyes
 */
@RestController
public class TestController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @RequestMapping("/test1/aa")
    public Test<CityMessage> test1() {
        Test<CityMessage> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");
        CityMessage city = new CityMessage();
        city.setId("123");
        city.setCity("asda");
        city.setSex("man");
        city.setUsername("null");
        List<CityMessage> list = new ArrayList<>();

        int num = 1000;

        for (int i = 0; i < num; i++) {
            list.add(city);
        }

        test.setData(list);

        return test;
    }

    @RequestMapping("/checklogin")
    public Test<Login> loginTest(String username, String password, int role, HttpSession session,
            HttpServletResponse response) {
        // 假设数据库中查询到了该用户，这里测试先所及生成一个UUID，作为用户的id
        String tusername,tpassword;
        if (role==2) {
            tusername = adminService.getAll1(username).getAdminName().toString();
            tpassword = adminService.getAll1(username).getAdminPassword().toString();
        } else {
            tusername = userService.Sel1("admin").getUserName().toString();
            tpassword = userService.Sel1("admin").getPassWord().toString();
        }
        if (tusername.equals(username) && tpassword.equals(password)) {

            String userId = UUID.randomUUID().toString();

            // 准备存放在IWT中的自定义数据
            Map<String, Object> info = new HashMap<>();
            info.put("username", username);
            info.put("pass", password);

            // 生成JWT字符串
            String token = JwtUtil.sign(userId, info);

            Test<Login> test = new Test<>();
            test.setCode(0);
            test.setCount(1000);
            test.setMsg("");

            List<Login> list = new ArrayList<>();
            Login login = new Login();
            login.setToken(token);
            list.add(login);
            test.setData(list);
            session.setAttribute("token", token);
            response.addHeader("token", token);

            int Id = userService.Sel1("admin").getId();

            session.setAttribute("userId", Id);
            session.setAttribute("role", role);

            return test;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/getval")
    public Test<String> getVal(HttpSession session) {
        Test<String> test = new Test<>();
        test.setCode(0);
        test.setCount(1000);
        test.setMsg("");

        String aa = session.getAttribute("token").toString();
        aa = "";
        List<String> list = new ArrayList<>();
        list.add(aa);

        test.setData(list);

        return test;
    }
}