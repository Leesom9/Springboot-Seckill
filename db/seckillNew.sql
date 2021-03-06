--创建数据局
CREATE DATABASE seckillNew DEFAULT CHARACTER SET utf8;

--切换数据库
use seckillNew

DROP TABLE IF EXISTS `seckill`;
DROP TABLE IF EXISTS `seckill_order`;

--创建商品秒杀表
CREATE TABLE `seckill`(
    `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `title` VARCHAR (1000) DEFAULT NULL COMMENT '商品名称',
    `image` VARCHAR (1000) DEFAULT NULL COMMENT '商品图片',
    `price` DECIMAL (10,2) DEFAULT NULL COMMENT '商品价格',
    `kill_price` DECIMAL (10,2) DEFAULT NULL COMMENT '秒杀价格',
    `stock_count` bigint DEFAULT NULL COMMENT '库存数量',
    `start_time` TIMESTAMP DEFAULT '2000-01-01 00:00:01' COMMENT '秒杀开始时间',
    `end_time` TIMESTAMP DEFAULT '2000-01-01 00:00:01' COMMENT '秒杀结束时间',
    `create_time` TIMESTAMP DEFAULT '2000-01-01 00:00:01' COMMENT '创建秒杀时间',
    PRIMARY KEY (`seckill_id`),
    KEY `idx_start_time` (`start_time`),
    KEY `idx_end_time` (`end_time`),
    KEY `idx_create_time` (`create_time`)
)CHARSET=UTF8 AUTO_INCREMENT=1000 ENGINE=InnoDB COMMENT '秒杀商品表';

--初始化数据
  insert into seckill
    (title,image,price,kill_price,stock_count,start_time,end_time)
  values
    ('5999元秒杀iPhoneXS','',1099,5999,100,'2019-08-20 00:00:00','2019-08-21 00:00:00'),
    ('7999元秒杀MacBook','',13888,7999,10,'2019-08-20 00:00:00','2019-08-21 00:00:00'),
    ('999元秒杀airpods2','',1599,999,200,'2019-08-20 00:00:00','2019-08-21 00:00:00'),
    ('6999元秒杀ipad pro','',9999,6999,50,'2019-08-20 00:00:00','2019-08-21 00:00:00');

--创建秒杀订单表
CREATE TABLE `seckill_order`(
    `seckill_id` bigint NOT NULL COMMENT '商品ID',
    `money` DECIMAL (10,2) NOT NULL COMMENT '支付金额',
    `user_phone` bigint NOT NULL COMMENT '用户电话',
    `create_time` timestamp NOT NULL COMMENT '创建时间',
    `state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1->无效，0->成功，1->已付款，2->已发货。。。 ',
    PRIMARY KEY (seckill_id,user_phone) ,/* 联合主键*/
    key `idx_create_time`(create_time)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

--InnoDB是支持事务的，且InnoDB相比MyISAM在并发上更具有高性能的优点。
--Mybatis会自动为tinyint类型的数据转换成true或false（0:false; 1 or 1+:true）。

--创建用户表
DROP TABLE IF EXISTS`seckill_user`;
CREATE TABLE `seckill_user`(
    `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(64) DEFAULT NULL COMMENT '账号',
    `password` varchar(64) DEFAULT NULL COMMENT '密码',
    `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
    `user_phone` varchar(64) DEFAULT NULL COMMENT '电话',
    PRIMARY KEY(`user_id`),
    UNIQUE KEY `idx_username` (`username`),
    UNIQUE KEY `idx_user_phone` (`user_phone`)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员表';

INSERT INTO seckill_user
    (username,password,nickname,user_phone)
    VALUES
    ('admin','admin','admin','123456');