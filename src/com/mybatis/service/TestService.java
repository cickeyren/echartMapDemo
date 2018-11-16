package com.mybatis.service;

import java.util.List;
import java.util.Map;

public interface TestService {

	public List<Map<String, Object>> countCity()throws Exception;
	public List<Map<String, Object>> listCity(String cityName)throws Exception;
}
