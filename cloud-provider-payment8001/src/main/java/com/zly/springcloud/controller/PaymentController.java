package com.zly.springcloud.controller;

import com.zly.springcloud.entities.CommonResult;
import com.zly.springcloud.entities.Payment;
import com.zly.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int i = paymentService.create(payment);
        log.info("插入数据"+payment);
        if(i>0){
            return new CommonResult(200,"添加成功,serverPort"+serverPort,i);

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

    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String s:services) {
            log.info("服务名称："+s);
            //获取服务的实例
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            for (ServiceInstance ins:instances
                 ) {
                log.info(ins.getInstanceId()+"\t"+ins.getHost()+"\t"+ins.getPort()+"\t"+ins.getUri());
            }

        }

        return this.discoveryClient;

    }

    /**
     * 测试openfeign 提供的服务返回超时
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

}
