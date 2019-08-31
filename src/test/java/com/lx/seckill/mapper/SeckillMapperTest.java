package com.lx.seckill.mapper;

import com.lx.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application.yml")
@SpringBootTest
public class SeckillMapperTest {

    @Resource
    private SeckillMapper seckillMapper;

    @Test
    public void queryAll() {
        List<Seckill> allSeckill = seckillMapper.queryAll();
        for (Seckill seckill : allSeckill) {
            System.out.println(seckill);
        }
    }

    @Test
    public void queryById() {
        long seckillId = 1000;
        Seckill seckill = seckillMapper.queryById(seckillId);

        System.out.println(seckill);
    }

    @Test
    public void queryByOffset() {
        int offset = 0;
        int limit = 2;

        List<Seckill> seckills = seckillMapper.queryByOffset(offset,limit);

        for (Seckill seckill : seckills) {
            System.out.println(seckill.getTitle());
        }
    }

    @Test
    public void reduceStock() {
        long seckillId = 1000;
        Date date = new Date();

        int res = seckillMapper.reduceStock(seckillId,date);

        System.out.println(res);
    }
}