package com.zly.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-21
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "处理paymentInfo_OK-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "处理paymentInfo_TimeOut-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
