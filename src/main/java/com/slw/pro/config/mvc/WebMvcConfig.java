package com.slw.pro.config.mvc;

import com.slw.pro.config.CompositionSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebMvcConfig
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/25
 * @Version: 1.0.0
 **/
@Configuration
@EnableSpringHttpSession
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HttpSessionStrategy getSessionStrategy(){
        return new CompositionSessionStrategy();
    }

    @Bean
    public SessionRepository getSessionRepository(){
        return new MapSessionRepository();
    }

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
