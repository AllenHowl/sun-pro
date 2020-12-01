package com.slw.pro.controller.vo;

import lombok.Data;

/**
 * @ClassName: UserReq
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/12/1
 * @Version: 1.0.0
 **/
@Data
public class UserReq {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 性别（0-男， 1-女）
     */
    private Integer sex;

    /**
     * 出生年月日
     */
    private String birthDay;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
