package com.zly.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-21
 */
@Configuration
public class FeignConfig {
    /**
     * openFeign 的日志  然后在配置文件里配置
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
