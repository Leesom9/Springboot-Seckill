package com.lx.seckill.service.impl;

import com.lx.seckill.dto.Exposer;
import com.lx.seckill.dto.SeckillExecution;
import com.lx.seckill.entity.Seckill;
import com.lx.seckill.entity.SeckillOrder;
import com.lx.seckill.enums.SeckillStatEnum;
import com.lx.seckill.exception.RepeatKillException;
import com.lx.seckill.exception.SeckillCloseException;
import com.lx.seckill.exception.SeckillException;
import com.lx.seckill.mapper.SeckillMapper;
import com.lx.seckill.mapper.SeckillOrderMapper;
import com.lx.seckill.redis.RedisTemplateConfig;
import com.lx.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author ：leesom
 * @date ：Created in 2019/9/1 19:37
 * @description：秒杀服务实现类
 * @modified By：
 * @version: $
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //盐值,用来加密md5
    private final String salt = "@#ghy%^13BB&Y98";

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private final String redisKey = "seckill";

    @Override
    public List<Seckill> queryAll() {

        List<Seckill> seckillList = redisTemplate.boundHashOps(redisKey).values();

        if(seckillList == null || seckillList.size() == 0){
            //redis中没有数据
            seckillList = seckillMapper.queryAll();
            for(Seckill seckill:seckillList){

                redisTemplate.boundHashOps(redisKey).put(seckill.getSeckillId(),seckill);
            }
            logger.info("将数据库读取的数据放入缓存");
        }else{
            logger.info("findAll -> 从数据库中读取数据");
        }

                seckillMapper.queryAll();

        return seckillList;
    }

    @Override
    public Seckill queryById(long seckillId) {

        Seckill seckill = seckillMapper.queryById(seckillId);

        return seckill;
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        Seckill seckill = (Seckill) redisTemplate.boundHashOps(redisKey).get(seckillId);

        if(seckill == null){
            seckill = seckillMapper.queryById(seckillId);

            if(seckill == null){
                return new Exposer(false,seckillId);
            }
            redisTemplate.boundHashOps(redisKey).put(seckill.getSeckillId(),seckill);
            logger.info("RedisTemplate -> 从数据库中读取并放入缓存中");
        }else {
            logger.info("RedisTemplate -> 从缓存中读取");
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }

        //此方法不可逆
        String md5 = getMd5(seckillId);

        return new Exposer(true,seckillId,md5);
    }

    //生成MD5值
    private String getMd5(long seckillId){

        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

        return md5;
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, BigDecimal money, String md5) throws RepeatKillException, SeckillCloseException, SeckillException {

        if(md5 == null || !md5.equals(getMd5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }

        Date nowTime = new Date();

        try {

            //插入订单信息
            int insertCount = seckillOrderMapper.insertOrder(seckillId,userPhone,money);

            if(insertCount <= 0 ){
                throw new RepeatKillException("seckill repeated");
            }else{
                //减库存
                int updateCount = seckillMapper.reduceStock(seckillId,nowTime);

                if(updateCount <= 0){
                    throw new SeckillCloseException("seckill closed");
                }else{
                    //秒杀成功
                    SeckillOrder seckillOrder = seckillOrderMapper.queryByIdWithSeckill(seckillId,userPhone);

                    //更新缓存
                    Seckill seckill = (Seckill) redisTemplate.boundHashOps(redisKey).get(seckillId);
                    seckill.setStockCount(seckill.getStockCount()-1);
                    redisTemplate.boundHashOps(redisKey).put(seckill.getSeckillId(),seckill);

                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS,seckillOrder.getSeckill());
                }
            }
        }catch (RepeatKillException e){
            throw e;
        }catch (SeckillCloseException e){
            throw e;
        }catch (Exception e){
            logger.error(e.getMessage(),e);

            //编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error" + e.getMessage());
        }
    }
}
