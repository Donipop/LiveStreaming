package com.donipop.livestreaming.config;

import com.donipop.livestreaming.handler.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LoginIntercepter loginIntercepter = new LoginIntercepter();
        registry.addInterceptor(loginIntercepter)
                //확인할 경로
                .addPathPatterns()
                //제외할 경로
                .excludePathPatterns();

    }
}
