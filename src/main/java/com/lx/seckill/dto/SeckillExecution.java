package com.lx.seckill.dto;

import com.lx.seckill.entity.Seckill;
import com.lx.seckill.enums.SeckillStatEnum;

/**
 * @author ：leesom
 * @date ：Created in 2019/8/31 23:23
 * @description：秒杀执行
 * @modified By：
 * @version: $
 */
public class SeckillExecution {

    //商品ID
    private long seckillId;

    //秒杀状态
    private int state;

    //秒杀状态明细
    private String stateInfo;

    //秒杀成功商品明细
    private Seckill seckill;

    //秒杀成功
    public SeckillExecution(long seckillId, SeckillStatEnum statEnum,Seckill seckill){
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.seckill = seckill;
    }

    //秒杀失败
    public SeckillExecution(long seckillId, SeckillStatEnum statEnum){
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckill=" + seckill +
                '}';
    }
}
