package com.lx.seckill.exception;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/1 16:04
 * @description：秒杀异常
 * @modified By：
 * @version: $
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message){

        super(message);
    }

    public SeckillCloseException(String message, Throwable casue){

        super(message, casue);
    }
}
