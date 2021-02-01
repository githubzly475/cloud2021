package com.zly.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
  /*为这个接口做服务降级 就是建一个它的实现类
  * 方法步骤：1 建一个PaymentHystrixService 的实现类PaymentFallbackService
  * 2 在@FeignClient 中加上fallback = PaymentFallbackService.class
  * 3 要在PaymentFallbackService 类上加@Component 这个注解
  *   */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
