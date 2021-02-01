package com.zly.springcloud.alibaba.service;

import com.zly.springcloud.entities.CommonResult;
import com.zly.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-29
 */
@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        Payment payment = new Payment();
        return new CommonResult<Payment>(55555,"openFegin 返回错误",payment);
    }
}
