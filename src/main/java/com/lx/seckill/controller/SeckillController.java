package com.lx.seckill.controller;

import com.lx.seckill.dto.Exposer;
import com.lx.seckill.dto.SeckillExecution;
import com.lx.seckill.dto.SeckillResult;
import com.lx.seckill.entity.Seckill;
import com.lx.seckill.enums.SeckillStatEnum;
import com.lx.seckill.exception.RepeatKillException;
import com.lx.seckill.exception.SeckillCloseException;
import com.lx.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：leesom
 * @date ：Created in 2019/9/2 18:24
 * @description：controller层
 * @modified By：
 * @version: $
 */

@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private String SeckillList(Model model){

        List<Seckill> seckillList = seckillService.queryAll();
        model.addAttribute("seckillList",seckillList);

        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    private String SeckillDetail(@PathVariable("seckillId") Long seckillId, Model model){

        if(null == seckillId){
            return "redirect:/seckill/list";
        }

        Seckill seckill = seckillService.queryById(seckillId);

        if(null == seckill){
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill",seckill);

        return "detial";
    }

    @RequestMapping(value = "{seckillId}/exposer",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){

        SeckillResult<Exposer> result = null;

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @PathVariable("money") BigDecimal money,
                                                   @CookieValue(value = "killPhone", required = false) Long phone){

        if(null == phone){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }

        try {

            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId,phone,money,md5);

            return new SeckillResult<SeckillExecution>(true,seckillExecution);

        }catch (RepeatKillException e){
            SeckillExecution repeatKillException = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,repeatKillException);
        }catch (SeckillCloseException e){
            SeckillExecution seckillCloseException = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true,seckillCloseException);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution innerError = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,innerError);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
