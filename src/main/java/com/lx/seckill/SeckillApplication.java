package com.lx.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.lx.seckill.mapper"})
public class SeckillApplication{

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

}
