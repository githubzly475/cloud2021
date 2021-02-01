package com.zly.springcloud.controller;

import com.zly.springcloud.entities.CommonResult;
import com.zly.springcloud.entities.Payment;
import com.zly.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-18
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int i = paymentService.create(payment);
        log.info("插入数据"+payment);
        if(i>0){
            return new CommonResult(200,"添加成功,端口:"+serverPort,i);
        }
        return new CommonResult(201,"添加失败",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("查询数据:"+payment);
        if(null !=payment){
            return new CommonResult(200,"查询成功,端口:"+serverPort,payment);
        }
        return new CommonResult(201,"查询失败",null);
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

}
