package com.mybatis.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mybatis.dao.TestMapper;
import com.mybatis.service.TestService;


@Service
@Transactional
public class TestServiceImpl implements TestService{
	@Autowired
	private TestMapper testMapper;

	@Override
	public List<Map<String, Object>> countCity() throws Exception {
		// TODO Auto-generated method stub
		return testMapper.countCity();
	}

	@Override
	public List<Map<String, Object>> listCity(String cityName) throws Exception {
		// TODO Auto-generated method stub
		return testMapper.listCity(cityName);
	}

}
