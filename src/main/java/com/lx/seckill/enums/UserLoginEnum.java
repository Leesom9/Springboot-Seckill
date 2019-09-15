package com.lx.seckill.enums;

public enum UserLoginEnum {
    SUCCESS(1,"登录成功"),
    PASSWORD_ERROR(2,"密码错误"),
    NO_ACCOUNT_EXISTS(0,"账号不存在"),
    ACCOUNT_CLOSURE(-1,"账号被封禁");

    UserLoginEnum(int loginState, String loginStateInfo) {
        this.loginState = loginState;
        this.loginStateInfo = loginStateInfo;
    }

    //登录状态
    private int loginState;
    //状态描述
    private String loginStateInfo;

    public int getLoginState() {
        return loginState;
    }

    public String getLoginStateInfo() {
        return loginStateInfo;
    }

    public static UserLoginEnum loginStateOf(int index){

        for(UserLoginEnum loginEnum : values()){
            if(loginEnum.loginState == index){
                return loginEnum;
            }
        }

        return null;
    }
}
