package com.zly.springcloud.alibaba.service.impl;

import com.zly.springcloud.alibaba.dao.OrderDao;
import com.zly.springcloud.alibaba.domain.Order;
import com.zly.springcloud.alibaba.service.AccountService;
import com.zly.springcloud.alibaba.service.OrderService;
import com.zly.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-29
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    //seata的事务注解
    @GlobalTransactional(name="zly-create-order" ,rollbackFor=Exception.class)
    public void create(Order order) {

        // 增加订单  扣库存  改余额   更改订单状态

        orderDao.create(order);

        storageService.decrease(order.getProductId(),order.getCount());

        accountService.decrease(order.getUserId(),order.getMoney());

        orderDao.update(order.getUserId(),0);
    }
}
