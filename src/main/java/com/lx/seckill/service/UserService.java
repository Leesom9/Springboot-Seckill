package com.lx.seckill.service;

import com.lx.seckill.entity.UserEntity;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/15 14:20
 * @description：用户服务层接口
 * @modified By：
 * @version: $
 */
public interface UserService {

    /***
     * 用户登录
     * @param userName
     * @return
     */
    UserEntity userLogin(String userName);
}
