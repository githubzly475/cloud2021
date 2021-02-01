package com.zly.springcloud.controller;

import com.zly.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zly
 * @Description :
 * @Date: 2021-01-26
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping("/sendMessage")
    public String send(){
       return iMessageProvider.send();
    }

}
