package com.lx.seckill.exception;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/1 16:04
 * @description：秒杀异常
 * @modified By：
 * @version: $
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message){
        super(message);
    }

    public SeckillException(String message, Throwable casue){
        super(message, casue);
    }
}
