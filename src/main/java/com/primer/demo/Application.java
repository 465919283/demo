package com.primer.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@MapperScan("com.primer.demo.mybatis.mapper")
@PropertySource(value = {"classpath:application.yml"}, ignoreResourceNotFound = true)
@EnableCaching
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableAsync
@Slf4j
@SpringBootApplication
@EnableTransactionManagement 
public class Application {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(){
		return "Hello World!";
	}
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setRegisterShutdownHook(true);
        springApplication.run(args);
    }
}
