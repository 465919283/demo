package com.primer.demo;

import com.primer.demo.service.BadStrategyService;
import com.primer.demo.service.StrategySelector;
import com.primer.demo.service.StrategyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wesley
 * @create 2019/11/22
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {
    @Resource
    private BadStrategyService badStrategyService;

    @Resource
    private StrategySelector selector;

    @RequestMapping(path = "/bad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String process(String type) {
        return badStrategyService.sayHello(type);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String process2(String type) {
        return selector.getService(type).sayHello(type);
    }
}
