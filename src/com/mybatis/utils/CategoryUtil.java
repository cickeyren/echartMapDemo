package com.mybatis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 权限重构工具类
 * @author rensq
 *
 */
public class CategoryUtil {
   
	public static final Logger logger = LoggerFactory.getLogger(CategoryUtil.class);
	
    public static boolean reConfig(String requestid,String categoryid){
    	try {
    		//从配置文件中获取host_url
    	    String hostUrl = PropertiesUtil.getProperty("host_url");
    		//权限重构的地址url
    		String url = hostUrl+"/workflow/category/formServiceTest.jsp";
    	    //发送请求
    		//参数requestid和categoryid
			HttpRequestUtil.httpGet1(url+"?requestid="+requestid+"&categoryid="+categoryid);  
        	return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("权限重构失败");
            return false;
        }
    }
    
	public static void main(String[] args) {

	}
}
