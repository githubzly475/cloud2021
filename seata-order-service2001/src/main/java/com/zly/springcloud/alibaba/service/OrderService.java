package com.zly.springcloud.alibaba.service;


import com.zly.springcloud.alibaba.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;


public interface OrderService {

    void create(Order order);

}
