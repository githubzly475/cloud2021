package com.zly.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zly.springcloud.service.PaymentHystrixService;
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
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/customer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){

     return  paymentHystrixService.paymentInfo_OK(id);
    }


    @GetMapping("/customer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })*/
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    /**
     * 下面是全局fallback方法 \
     *每个方法都要有个兜底方法  这样会造成代码膨胀
     * 解决方法：配置一个全局的方法:
     *   第一步：在类上加注解@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
     *   第二步：在需要处理的方法上加 @HystrixCommand
      */
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }


    /*如上做也会出现一个问题就是每个方法都要加上@HystrixCommand 这个注解  这样代码很混乱
    * 解决办法：只需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可实现解耦
    * 也就是说 在这里只要给PaymentHystrixService 做服务降级处理就行了，具体实现看PaymentHystrixService这个接口
    * */

}
