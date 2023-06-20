package com.example.demo.interceptor;

import com.example.demo.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author my_eyes
 */

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String temp = request.getSession().getAttribute("token").toString();
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        token = temp;
        System.out.println("此处测试是否拿到了token：" + token);

        if (token == null) {
            throw new RuntimeException("无 token ，请重新登陆");
        }

        // 验证 token
        JwtUtil.checkSign(token);

        // 验证通过后， 这里测试取出JWT中存放的数据
        // 获取 token 中的 userId
        String userId = JwtUtil.getUserId(token);
        System.out.println("id : " + userId);
        System.out.println(request.getSession().getAttribute("role"));

        // 获取 token 中的其他数据
        Map<String, Object> info = JwtUtil.getInfo(token);
        info.forEach((k, v) -> System.out.println(k + ":" + v));
        return true;
    }
}
