package com.slw.pro.config.mvc;

import com.slw.pro.component.BusinessException;
import com.slw.pro.component.constant.WebConstant;
import com.slw.pro.config.RequiredLogin;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @ClassName: LoginHandlerInterceptor
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/25
 * @Version: 1.0.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");// 支持js跨域

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        RequiredLogin requiredLogin = AnnotationUtils.findAnnotation(method, RequiredLogin.class);
        if (requiredLogin == null)
        {
            return true;
        }
        HttpSession session = request.getSession(false);

        if (session != null) {
            Object attribute = session.getAttribute(WebConstant.SESSION_USER);

            if (attribute != null) {
                return true;
            }
        }

        throw new BusinessException(1000,"请先登录！");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
