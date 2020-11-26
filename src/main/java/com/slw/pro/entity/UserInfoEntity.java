package com.slw.pro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author fjy
 * @since 2020-11-17
 */
@TableName("user_info")
@Data
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
      @TableId(value = "id", type = IdType.AUTO)
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
