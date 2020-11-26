package com.slw.pro.config.mvc;

import com.slw.pro.component.constant.WebConstant;
import com.slw.pro.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: AccessLogInterceptor
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/25
 * @Version: 1.0.0
 **/
@Slf4j(topic = "accessLog")
public class AccessLogInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String url = request.getRequestURI();
            String remote = request.getRemoteAddr();

            String headerString = RequestUtil.requestHeaderToString(request);
            String paramString = RequestUtil.requestParamToString(request);

            Long uid = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute(WebConstant.SESSION_USER);
                if (user != null) uid = user.getUid();
            }

            log.info("URL:{} remote:{} uid:{} header:{} params:{}", url, remote, uid, headerString, paramString);
        } catch (Exception e) {
            log.error("portal access interceptor error:", e);
        }

        return true;
    }
}
