package com.lx.seckill.dto;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/15 17:04
 * @description：登录结果
 * @modified By：
 * @version: $
 */
public class UserLoginResult<T> {

    private boolean success;

    private T Data;

    private String error;

    public UserLoginResult(boolean success, T data) {
        this.success = success;
        Data = data;
    }

    public UserLoginResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
