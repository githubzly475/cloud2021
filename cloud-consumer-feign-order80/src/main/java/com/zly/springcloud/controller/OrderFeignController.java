package com.zly.springcloud.controller;

import com.zly.springcloud.entities.CommonResult;
import com.zly.springcloud.entities.Payment;
import com.zly.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-21
 */
@RestController
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/customer/feign/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
      return paymentFeignService.getPaymentById(id);

    }

    @GetMapping("/customer/feign/timeout")
    public String paymentFeignTimeout(){
       return paymentFeignService.paymentFeignTimeout();
    }

}
