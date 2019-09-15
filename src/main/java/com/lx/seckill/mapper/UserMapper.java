package com.lx.seckill.mapper;

import com.lx.seckill.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/15 13:31
 * @description：用户登录
 * @modified By：
 * @version: $
 */
@Repository
public interface UserMapper {

    UserEntity userLogin(String userName);
}
