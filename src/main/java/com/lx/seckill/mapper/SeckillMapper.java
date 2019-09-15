package com.lx.seckill.mapper;

import com.github.pagehelper.Page;
import com.lx.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ：leesom
 * @date ：Created in 2019/8/30 09:27
 * @description：商品信息相关操作接口
 * @modified By：
 * @version: $
 */
@Repository
public interface SeckillMapper {

    /***
     * 查询所有商品
     * @return
     */
    List<Seckill> queryAll();

    /***
     * 分页查询所有商品
     * @return
     */
    Page<Seckill> queryAllByPage(Seckill seckill);

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
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime")Date killTime);
}
