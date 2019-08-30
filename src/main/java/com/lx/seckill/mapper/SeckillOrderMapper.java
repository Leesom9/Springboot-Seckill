package com.lx.seckill.mapper;

import com.lx.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author ：leesom
 * @date ：Created in 2019/8/30 09:27
 * @description：订单信息相关操作接口
 * @modified By：
 * @version: $
 */
@Mapper
public interface SeckillOrderMapper {

    /***
     * 插入订单明细
     * @param seckillId
     * @param userPhone
     * @param money
     * @return 返回执行SQL更新的记录行数，如果>=1则更新成功
     */
    int insertOrder(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone,
                    @Param("money")BigDecimal money);

    /***
     * 查询订单明细，并且携带商品明细
     * @param seckillId
     * @param userPhone
     * @return
     */
    SeckillOrder queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
