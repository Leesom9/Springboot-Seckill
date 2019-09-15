package com.lx.seckill.mapper;

import com.lx.seckill.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserEntityMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void userLogin() {
        String userName = "admin";
        UserEntity userEntity = userMapper.userLogin(userName);

        System.out.println(userEntity);
    }
}