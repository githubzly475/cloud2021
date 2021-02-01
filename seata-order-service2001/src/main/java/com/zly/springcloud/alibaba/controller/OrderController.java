package com.zly.springcloud.alibaba.controller;

import com.zly.springcloud.alibaba.domain.CommonResult;
import com.zly.springcloud.alibaba.domain.Order;
import com.zly.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-29
 */
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
