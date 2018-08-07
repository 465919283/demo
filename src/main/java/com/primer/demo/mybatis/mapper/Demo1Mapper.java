package com.primer.demo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Demo1Mapper {
	public List<Integer> queryWomanList(@Param("manId") Integer manId);
	public void insert(@Param("manId")int manId,@Param("womanId")int womanId);
	public void delete(@Param("manId")int manId,@Param("womanId")int womanId);
}
