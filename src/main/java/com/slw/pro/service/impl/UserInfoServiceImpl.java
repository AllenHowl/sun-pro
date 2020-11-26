package com.slw.pro.service.impl;

import com.slw.pro.entity.UserInfoEntity;
import com.slw.pro.mapper.UserInfoMapper;
import com.slw.pro.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fjy
 * @since 2020-11-17
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {

}
