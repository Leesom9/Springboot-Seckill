package com.lx.seckill.service.impl;

import com.lx.seckill.entity.UserEntity;
import com.lx.seckill.mapper.UserMapper;
import com.lx.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/15 14:21
 * @description：用户服务层实现
 * @modified By：
 * @version: $
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity userLogin(String userName) {

        return userMapper.userLogin(userName);
    }
}
