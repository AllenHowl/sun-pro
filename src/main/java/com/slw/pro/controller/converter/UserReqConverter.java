package com.slw.pro.controller.converter;

import com.slw.pro.controller.vo.UserReq;
import com.slw.pro.entity.UserInfoEntity;

/**
 * @ClassName: UserReqConverter
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/12/1
 * @Version: 1.0.0
 **/
public class UserReqConverter {

    public static UserInfoEntity userReq2UserEntity(UserReq userReq){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        if (userReq == null) {
            return userInfoEntity;
        }
        Long nowTime = System.currentTimeMillis();
        userInfoEntity.setBirthDay(userReq.getBirthDay());
        userInfoEntity.setCreateTime(nowTime);
        userInfoEntity.setEmail(userReq.getEmail());
        userInfoEntity.setNickName(userReq.getNickName());
        userInfoEntity.setPhone(userReq.getPhone());
        userInfoEntity.setSex(userReq.getSex());
        userInfoEntity.setUpdateTime(nowTime);
        userInfoEntity.setPassword(userReq.getPassword());
        return userInfoEntity;
    }
}
