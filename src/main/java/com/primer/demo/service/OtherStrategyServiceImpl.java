package com.primer.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Wesley
 * @create 2019/11/22
 */
@Service
public class OtherStrategyServiceImpl implements StrategyService {
    @Override
    public String sayHello(String type) {
        return "我不认识你";
    }

    @Override
    public String getType() {
        return "other";
    }
}
