package com.primer.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Wesley
 * @create 2019/11/22
 */
@Service
public class RiceStrategyServiceImpl implements StrategyService {
    @Override
    public String sayHello(String type) {
        return "你好，大米";
    }

    @Override
    public String getType() {
        return "rice";
    }
}
