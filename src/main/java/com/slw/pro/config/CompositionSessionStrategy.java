package com.slw.pro.config;

import com.slw.pro.component.constant.WebConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CompositionSessionStrategy
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/26
 * @Version: 1.0.0
 **/
@Slf4j
public class CompositionSessionStrategy implements HttpSessionStrategy {


    @Override
    public String getRequestedSessionId(HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getHeader(WebConstant.HEADER_SESSION_ID);
        return sessionId;
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        httpServletResponse.setHeader(WebConstant.HEADER_SESSION_ID,session.getId());
    }

    @Override
    public void onInvalidateSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader(WebConstant.HEADER_SESSION_ID, "");
    }

}
