package com.mybatis.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Description:Properties文件常用方法工具类
 * 
 * @author rensq
 * @date 2018-8-13
 */
public class PropertiesUtil {

	private static final String path = "/system.properties";
	public static final Resource resource = new ClassPathResource(path);
	public static Properties props = null;
	static {
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取指定key的value
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	
	public static void main(String[] args) {
		System.out.println(getProperty("chinese_name"));
	}
}