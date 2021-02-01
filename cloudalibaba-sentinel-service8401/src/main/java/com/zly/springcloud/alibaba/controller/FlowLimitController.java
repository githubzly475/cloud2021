package com.zly.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-28
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }
}
