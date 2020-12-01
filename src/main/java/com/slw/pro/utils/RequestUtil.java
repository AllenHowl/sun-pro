package com.slw.pro.utils;

import com.slw.pro.component.constant.WebConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName: RequestUtil
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/25
 * @Version: 1.0.0
 **/
public class RequestUtil {


    public static String requestHeaderToString(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("{");
        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            String value = request.getHeader(key);
            buffer.append("\"").append(key).append("\":");
            buffer.append("\"").append(value).append("\",");
        }

        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("}");
        return buffer.toString();
    }

    public static String requestParamToString(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("{");
        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String key = (String)paramNames.nextElement();
            String value = request.getParameter(key);
            buffer.append("\"").append(key).append("\":");
            buffer.append("\"").append(value).append("\",");
        }

        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("}");
        return buffer.toString();
    }

    public static Long getLonginUid(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Long) request.getAttribute(WebConstant.ATTRIBUTE_UID);
    }
}
