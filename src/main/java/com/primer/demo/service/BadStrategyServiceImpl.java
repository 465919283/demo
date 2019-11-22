package com.primer.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Wesley
 * @create 2019/11/22
 */
@Service
public class BadStrategyServiceImpl implements BadStrategyService {
    @Override
    public String sayHello(String type) {
        if ("rice".equals(type)){
            return "你好，大米";
        }else if ("wheat".equals(type)){
            return "你好，小麦";
        }else {
            return "我不认识你";
        }
    }
}
