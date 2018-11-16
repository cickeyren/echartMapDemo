package com.mybatis.utils;

import java.util.Date;
import java.util.UUID;

/**
 * uuid
 * 主键生成
 * 去除"-"
 * @author rensq
 *
 */
public class UUIDUtil {
	public final static String DJBHQZ = PropertiesUtil.getProperty("dhqz"); 
	
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }
    
    //单号生成规则：yyyy+4位随机数字
    public static String getDJBH(){
    	int i = (int)((Math.random()*9+1)*1000);
        return DJBHQZ+DateUtil.format(new Date(), "yyyyMMdd")+i;
    } 
    
	public static void main(String[] args) {
		System.out.println(generateUUID());
		System.out.println(getDJBH());  
	}
}
