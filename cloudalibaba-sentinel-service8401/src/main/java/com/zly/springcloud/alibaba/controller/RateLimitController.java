package com.zly.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zly.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.zly.springcloud.entities.CommonResult;
import com.zly.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-28
 */
@RestController
public class RateLimitController {


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception)
    {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }

    //分离兜底方法

    @GetMapping("/rateLimit/fenli")
    @SentinelResource(value = "fenli",blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "handlerException2")
    //如此配置 兜底方法就是CustomerBlockHandler这个类里的 handlerException2 这个方法
    public CommonResult fenli()
    {
        return new CommonResult(200,"按客戶自定义");
    }



}
