package com.slw.pro.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.slw.pro.component.BusinessException;
import com.slw.pro.component.FrameResp;
import com.slw.pro.component.constant.ErrorConstant;
import com.slw.pro.component.constant.WebConstant;
import com.slw.pro.config.RequiredLogin;
import com.slw.pro.entity.UserInfoEntity;
import com.slw.pro.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
*   @Description: 用户信息控制
*   @Date: 2020/11/17
*   @Param:
*   @Return:
*   @Version: 1.0.0
**/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/get")
    @RequiredLogin
    public FrameResp queryUserInfo() throws Exception{
        log.info("UserInfoController queryUserInfo");
        UserInfoEntity userInfoEntity = userInfoMapper.getUserInfo(1l);
        log.info("userInfoEntities = {}", JSON.toJSONString(userInfoEntity));
        if (userInfoEntity == null ) {
            throw new BusinessException(ErrorConstant.NO_USER);
        }
        return FrameResp.buildSuccess()
                .buildBody("user",userInfoEntity);
    }


    @RequestMapping("/login")
    public FrameResp userLogin(String mail, String password) throws Exception{
        log.info("UserInfoController userLogin");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email",mail);
        queryWrapper.eq("password",password);
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(queryWrapper);
        if (userInfoEntity != null) {
            dealSession(userInfoEntity.getId());
        }
        log.info("userInfoEntities = {}", JSON.toJSONString(userInfoEntity));
        if (userInfoEntity == null ) {
            throw new BusinessException(ErrorConstant.NO_USER);
        }
        return FrameResp.buildSuccess()
                .buildBody("user",userInfoEntity);
    }


    /**
    *   @Description: 处理会话session的创建
    *   @Date: 2020/11/30
    *   @Param: [uid：用户id]
    *   @Return: void
    *   @Version: 1.0.0
    **/
    private void dealSession(Long uid){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        HttpSession session = request.getSession(true);
        session.setAttribute(WebConstant.SESSION_USER,uid);
        session.setMaxInactiveInterval(WebConstant.SESSION_ALIVE_TIME);
        response.setHeader(WebConstant.HEADER_SESSION_ID,session.getId());
    }

}

