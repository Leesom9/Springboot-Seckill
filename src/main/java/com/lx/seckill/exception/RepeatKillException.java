package com.lx.seckill.exception;

import com.lx.seckill.dto.SeckillExecution;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/1 16:04
 * @description：秒杀异常
 * @modified By：
 * @version: $
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message, Throwable casue){

        super(message, casue);
    }
}
