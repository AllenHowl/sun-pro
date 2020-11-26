package com.slw.pro.config.mvc;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebMvcConfig
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/25
 * @Version: 1.0.0
 **/
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 日志拦截
        registry.addInterceptor(new AccessLogInterceptor()).addPathPatterns("/**");
        // 登录拦截
        registry.addInterceptor(new LoginHandlerInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/user/registry");
    }
}
