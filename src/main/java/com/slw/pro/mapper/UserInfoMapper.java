package com.slw.pro.mapper;

import com.slw.pro.entity.UserInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author fjy
 * @since 2020-11-17
 */
@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

    @Select("select * from user_info where id = ${id}")
    UserInfoEntity getUserInfo(@Param("id")Long id);
}
