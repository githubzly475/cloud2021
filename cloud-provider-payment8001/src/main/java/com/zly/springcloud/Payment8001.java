package com.zly.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @Description :
 * @Date: 2021-01-15
 */
@SpringBootApplication
@EnableEurekaClient
/*对于注册eureka里面的微服务,可以通过服务发现来获得该服务的信息*/
@EnableDiscoveryClient
public class Payment8001 {
    public static void main(String[] args) {
        SpringApplication.run(Payment8001.class,args);
    }

}
