package com.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TestMapper {


	public List<Map<String, Object>> countCity()throws Exception;
	public List<Map<String, Object>> listCity(String cityName)throws Exception;
}
