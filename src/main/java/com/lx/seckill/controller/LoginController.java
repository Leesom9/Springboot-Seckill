package com.lx.seckill.controller;

import com.lx.seckill.dto.UserLoginResult;
import com.lx.seckill.entity.UserEntity;
import com.lx.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/6 19:49
 * @description：用户控制类
 * @modified By：
 * @version: $
 */

@Controller
@RequestMapping(value = "/user")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index() {
        return "page/login";
    }

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public UserLoginResult<UserEntity> login(
            @PathVariable("username") String username,
            @PathVariable("password") String password) {

        UserEntity userEntity = userService.userLogin(username);

        if(null == userEntity){
            return new UserLoginResult<UserEntity>(false,"账号不存在");
        }

        if(!userEntity.getPassWord().equals(password)){
            return new UserLoginResult<UserEntity>(false,"密码错误");
        }

        return new UserLoginResult<UserEntity>(true,userEntity);
    }
}
