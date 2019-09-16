package com.lx.seckill.entity;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/15 13:35
 * @description：用户实体
 * @modified By：
 * @version: $
 */
public class UserEntity {

    //账号
    private String userName;

    //密码
    private String passWord;

    //昵称
    private String nickName;

    //电话
    private String userPhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
