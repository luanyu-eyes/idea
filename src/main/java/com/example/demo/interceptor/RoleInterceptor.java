package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class RoleInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String role = request.getSession().getAttribute("role").toString();
        if (role.equals("1")) {
            throw new RuntimeException("无权限 ，请重新登陆");
        }
        System.out.println("12321313");
            return true;
    }
}
