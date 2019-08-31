package com.lx.seckill.mapper;

import com.lx.seckill.entity.Seckill;
import com.lx.seckill.entity.SeckillOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillOrderMapperTest {

    @Resource
    private SeckillOrderMapper seckillOrderMapper;

    @Test
    public void insertOrder() {
        long seckillId = 1000;
        long phone = 13345678890L;
        BigDecimal money = new BigDecimal(5999);

        int res = seckillOrderMapper.insertOrder(seckillId,phone,money);

        System.out.println(res);

    }

    @Test
    public void queryByIdWithSeckill() {
        long seckillId = 1000;
        long phone = 13345678890L;

        SeckillOrder seckillOrder = seckillOrderMapper.queryByIdWithSeckill(seckillId,phone);

        System.out.println(seckillOrder);
        System.out.println(seckillOrder.getSeckill());
    }
}