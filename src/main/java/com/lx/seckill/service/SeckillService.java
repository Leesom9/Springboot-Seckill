package com.lx.seckill.service;

import com.lx.seckill.dto.Exposer;
import com.lx.seckill.dto.SeckillExecution;
import com.lx.seckill.entity.PageBean;
import com.lx.seckill.entity.Seckill;
import com.lx.seckill.exception.RepeatKillException;
import com.lx.seckill.exception.SeckillCloseException;
import com.lx.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：leesom
 * @date ：Created in 2019/8/31 20:41
 * @description：秒杀服务层接口
 * @modified By：
 * @version: $
 */
public interface SeckillService {

    /***
     * 查询所有商品明细
     * @return
     */
    List<Seckill> queryAll();


    /***
     * 分页查询所有商品明细
     * @param seckill
     * @param pageCode
     * @param pageSize
     * @return
     */
    PageBean queryAllByPage(Seckill seckill,int pageCode,int pageSize);

    /***
     * 按照id查询商品明细
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    /***
     * 暴露秒杀地址
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /***
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param money
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, BigDecimal money, String md5)
    throws RepeatKillException, SeckillCloseException, SeckillException;
}
