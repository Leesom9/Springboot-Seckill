package com.lx.seckill.mapper;

import com.lx.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ：leesom
 * @date ：Created in 2019/8/30 09:27
 * @description：商品信息相关操作接口
 * @modified By：
 * @version: $
 */
@Mapper
public interface SeckillMapper {

    /***
     * 查询所有商品
     * @return
     */
    List<Seckill> queryAll();

    /***
     * 根据ID查询秒杀商品信息
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /***
     * 根据偏移量查询商品信息
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryByOffset(@Param("offset") int offset, @Param("limit") int limit);

    /***
     * 减少库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceStock(@Param("seckillID") long seckillId, @Param("killTime")Date killTime);
}
