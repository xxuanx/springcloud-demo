package com.moon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @GetMapping("index")
    @ResponseBody
    public String index(HttpServletRequest request){
        log.info("request:" + request.getRequestURL());
        return "index page";
    }
}
