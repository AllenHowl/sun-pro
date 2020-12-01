package com.slw.pro.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.slw.pro.component.BusinessException;
import com.slw.pro.component.FrameResp;
import com.slw.pro.component.constant.ErrorConstant;
import com.slw.pro.component.constant.WebConstant;
import com.slw.pro.config.RequiredLogin;
import com.slw.pro.controller.converter.UserReqConverter;
import com.slw.pro.controller.vo.UserReq;
import com.slw.pro.entity.UserInfoEntity;
import com.slw.pro.service.UserInfoService;
import com.slw.pro.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private UserInfoService userInfoService;

    /**
    *   @Description: 查询用户信息接口
    *   @Date: 2020/12/1
    *   @Param: []
    *   @Return: com.slw.pro.component.FrameResp
    *   @Version: 1.0.0
    **/
    @RequestMapping("/get")
    @RequiredLogin
    public FrameResp queryUserInfo() throws Exception{
        log.info("UserInfoController queryUserInfo");
        Long longinUid = RequestUtil.getLonginUid();
        UserInfoEntity userInfoEntity = userInfoService.getById(longinUid);
        log.info("userInfoEntities = {}", JSON.toJSONString(userInfoEntity));
        if (userInfoEntity == null ) {
            throw new BusinessException(ErrorConstant.NO_USER);
        }
        return FrameResp.buildSuccess()
                .buildBody("user",userInfoEntity);
    }


    /**
    *   @Description: 用户登录接口，优先手机号，其次邮箱
    *   @Date: 2020/12/1
    *   @Param: [phone, email, password]
    *   @Return: com.slw.pro.component.FrameResp
    *   @Version: 1.0.0
    **/
    @RequestMapping("/login")
    public FrameResp userLogin(String phone, String email, String password) throws Exception{
        log.info("UserInfoController userLogin , phone = {} , mail = {}", phone, email);
        if ((StringUtils.isEmpty(phone) && StringUtils.isEmpty(email)) || StringUtils.isEmpty(password)) {
            throw new BusinessException(ErrorConstant.NO_USER);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("password",password);

        if (!StringUtils.isEmpty(phone)) {
            queryWrapper.eq("phone", phone);
        }else {
            queryWrapper.eq("email", email);
        }
        UserInfoEntity userInfoEntity = userInfoService.getOne(queryWrapper);
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
    *   @Description: 用户注册接口
    *   @Date: 2020/12/1
    *   @Param: [userReq]
    *   @Return: com.slw.pro.component.FrameResp
    *   @Version: 1.0.0
    **/
    @RequestMapping("/registry")
    public FrameResp userRegistry(UserReq userReq) throws Exception{
        log.info("UserInfoController userRegistry userReq = {}", userReq);
        if (userReq == null) {
            throw new BusinessException(ErrorConstant.ERROR_PARAMS);
        }
        if (!checkRegistry(userReq)) {
            throw new BusinessException(ErrorConstant.ERROR_PARAMS);
        }
        UserInfoEntity userInfoEntity = UserReqConverter.userReq2UserEntity(userReq);
        if (ObjectUtils.isEmpty(userInfoEntity)) {
            throw new BusinessException(ErrorConstant.ERROR_PARAMS);
        }
        boolean saveStatus = userInfoService.save(userInfoEntity);
        if (!saveStatus) {
            return FrameResp.buildError();
        }
        return FrameResp.buildSuccess()
                .buildBody("user",userInfoEntity);
    }

    private Boolean checkRegistry(UserReq userReq){
        if (userReq != null) {
            Boolean b = !(StringUtils.isEmpty(userReq.getPhone())
                    || StringUtils.isEmpty(userReq.getPassword())
                    || StringUtils.isEmpty(userReq.getNickName())
                    || StringUtils.isEmpty(userReq.getSex())
                    || StringUtils.isEmpty(userReq.getBirthDay()));
            if (b) {
                String path = "\\d{4}-\\d{2}-\\d{2}";
                Pattern pattern = Pattern.compile(path);
                Matcher matcher = pattern.matcher(userReq.getBirthDay());
                b = b && matcher.matches();
            }
            return b;
        }
        return false;
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

