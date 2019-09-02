package com.lx.seckill.dto;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/2 20:00
 * @description：秒杀结果集
 * @modified By：
 * @version: $
 */
public class SeckillResult<T> {

    private boolean result;

    private T date;

    private String error;

    //执行成功，返回数据
    public SeckillResult(boolean result, T date) {
        this.result = result;
        this.date = date;
    }

    //执行失败，返回原因
    public SeckillResult(boolean result, String error) {
        this.result = result;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
