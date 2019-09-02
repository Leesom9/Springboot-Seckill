package com.lx.seckill.controller;

import com.lx.seckill.dto.Exposer;
import com.lx.seckill.dto.SeckillExecution;
import com.lx.seckill.dto.SeckillResult;
import com.lx.seckill.entity.Seckill;
import com.lx.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                                   @CookieValue(value = "killPhone", required = false) Long phone){

        return null;

    }
}
