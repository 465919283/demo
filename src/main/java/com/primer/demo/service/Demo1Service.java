package com.primer.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.demo.mybatis.mapper.Demo1Mapper;

@Service
public class Demo1Service {

	@Autowired
	private Demo1Mapper demoMapper;
	
	@Transactional
	public void update(Integer manId,List<Integer> paramList){
		List<Integer> dbList = demoMapper.queryWomanList(manId);
		Collections.sort(dbList);
		Collections.sort(paramList);
		
		int dbSize = null == dbList ? 0 : dbList.size();
		int paramSize = null == paramList ? 0 : paramList.size();
		
		int dbIter = 0;
		int paramIter = 0;
		while(dbIter < dbSize || paramIter < paramSize){
			if(dbIter == dbSize){
				demoMapper.insert(manId, paramList.get(paramIter));
				paramIter += 1;
				continue;
			}
			if(paramIter == paramSize){
				demoMapper.delete(manId, dbList.get(dbIter));
				dbIter += 1;
				continue;
			}
			if(dbList.get(dbIter).intValue() == paramList.get(paramIter).intValue()){
				//do update
				dbIter += 1;
				paramIter += 1;
			}else if(dbList.get(dbIter).intValue() > paramList.get(paramIter).intValue()){
				demoMapper.insert(manId, paramList.get(paramIter));
				paramIter += 1;
			}else{
				demoMapper.delete(manId, dbList.get(dbIter));
				dbIter += 1;
			}
		}
	}
}
