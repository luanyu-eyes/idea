package com.example.demo.config;

import com.example.demo.interceptor.JwtInterceptor;
import com.example.demo.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author my_eyes
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 添加jwt拦截器，并指定拦截路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/wo1rk/**",  "/dele1t/**", "/ins1ert/**");
        
        registry.addInterceptor(RoleInterceptor())
                .addPathPatterns("/ad1min/**");
        
    }

    private RoleInterceptor RoleInterceptor() {
        return new RoleInterceptor();
    }

    /**
     * jwt拦截器
     */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
