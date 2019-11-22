package com.primer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Wesley
 * @create 2019/11/22
 */
@Component
public class StrategySelector {
    private Map<String, StrategyService> serviceMap;

    @Autowired
    public void setServices(List<StrategyService> services) {
        serviceMap = services.stream().collect(Collectors.toMap(StrategyService::getType, Function.identity(), (k1, k2) -> k2));
    }

    public StrategyService getService(String type) {
        return serviceMap.get(type);
    }
}
