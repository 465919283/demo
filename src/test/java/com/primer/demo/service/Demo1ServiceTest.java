package com.primer.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(false)
public class Demo1ServiceTest {

	@Autowired
	private Demo1Service demoService;

	@Transactional
	@Rollback(false)
	@Test
	public void testUpdate(){
		List<Integer> paramList = new ArrayList<>();
		paramList.add(5);
		paramList.add(3);
		paramList.add(2);
		
		demoService.update(1, paramList);
	}
}
