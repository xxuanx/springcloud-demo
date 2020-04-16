package com.moon.controller;

import com.moon.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 秒杀controller
 * @author wxb
 */
@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    private SecKillService secKillService;

    @GetMapping("/")
    @ResponseBody
    private String secKill(){
//        secKillService.seckill(1);
//        secKillService.seckillWithHandLock(1);
        secKillService.seckillWithAQSLock(1);
        return "Seckill";
    }

}
