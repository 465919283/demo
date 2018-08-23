package com.primer.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.primer.demo.util.ThreadPool;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(false)
public class Demo2ServiceTest {

	@Test
	public void test(){
		List<Callable<String>> callables = new ArrayList<>();
		for(int i=0;i<10;i++){
			callables.add(()-> Math.random()+"");
		}
		List<String> res = ThreadPool.addTask(callables);
		System.out.println("**********************");
		for(String str : res){
			System.out.println(str);
		}
	}
}
