package com.moon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 入口controller
 * @author macbookpro
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @GetMapping("index")
    @ResponseBody
    public String index(HttpServletRequest request){
        //http://localhost:8081/index
        log.info("request:" + request.getRequestURL());
        return "index page";
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();

    }

    @GetMapping
    @ResponseBody
    public String index1(){
        return "hello world";
    }



}
