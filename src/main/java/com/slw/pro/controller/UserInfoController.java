package com.slw.pro.controller;


import com.alibaba.fastjson.JSON;
import com.slw.pro.entity.UserInfoEntity;
import com.slw.pro.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String queryUserInfo(){
        log.info("UserInfoController queryUserInfo");
        UserInfoEntity userInfoEntity = userInfoMapper.getUserInfo(1l);
        log.info("userInfoEntities = {}", JSON.toJSONString(userInfoEntity));
        return userInfoEntity.toString();
    }
}

