package com.mybatis.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class StringHelper {

	public static String null2String(String strIn) {
		return strIn == null ? "" : strIn;
	}
	
	public static String replaceStr(Object obj,String replaceStr) {
		if(obj==null || "".equals(obj) || "null".equals(obj)){
			return "";
		}else {
			String str = obj.toString();
			str = str.replace(replaceStr, "");
			return str;
			
		}
	}
	/**
	 * 截取字符串
	 * @param strIn
	 * @return
	 */
	public static String cutString(String strIn) {
		String retStr = "";
		String [] strs = strIn.split("\\\\");
		for(int i=0;i<strs.length;i++){
			if (i>4) {
				if(retStr != ""){
					retStr +="\\" + strs[i];
				}else {
					retStr = strs[i];
				}
				
			}
		}
		return retStr;
	}
	
	/**
	 * 对象是否是空
	 * true是空
	 * false不是空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null || "".equals(obj) || "null".equals(obj)){
			return true;
		}
		return false;
	}
	
    public static String ajaxEncoding(String str){
        try {
            str=URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    public static String cutJpgString(String str){
    	String ret = "";
        try {
			String name = str.substring(0, str.length()-4);
    		ret += name +"_yt.jpg";
			
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public static boolean iscontains(List<Map<String, Object>> list,String contStr){
        try {
        	if(list.size() >0){
        		String str = list.toString();
        		if (contStr != null && contStr != "") {
        			return str.contains(contStr);
				}else {
					 return false;
				}
        	}else {
        		 return false;
			}
			
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
    }
    
	public static void main(String[] args) {
		Random ra =new Random();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		
		List<Map<String, Object>> list0 = new ArrayList<Map<String, Object>>(); 
		
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>(); 
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "rensq");
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", 2);
		map1.put("name", "wangwu");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", 3);
		map2.put("name", "ty");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("id", 4);
		map3.put("name", "rr");
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("id", 5);
		map4.put("name", "rt");
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("id", 6);
		map5.put("name", "ww");
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("id", 7);
		map6.put("name", "qw");
		Map<String, Object> map7 = new HashMap<String, Object>();
		map7.put("id", 8);
		map7.put("name", "wue");
		Map<String, Object> map8 = new HashMap<String, Object>();
		map8.put("id", 9);
		map8.put("name", "chenz");
		Map<String, Object> map9 = new HashMap<String, Object>();
		map9.put("id", 10);
		map9.put("name", "wang");
		
		Map<String, Object> map10 = new HashMap<String, Object>();
		map10.put("id", 0);
		map10.put("name", "rensq");
		Map<String, Object> map11 = new HashMap<String, Object>();
		map11.put("id", 0);
		map11.put("name", "chenz");
		
		Map<String, Object> map12 = new HashMap<String, Object>();
		map12.put("id", 0);
		map12.put("name", "liu");
		
		list1.add(map);
		list1.add(map1);
		list1.add(map2);
		list1.add(map3);
		list1.add(map4);
		list1.add(map5);
		list1.add(map6);
		
		list2.add(map7);
		list2.add(map8);
		list2.add(map9);
		
		list.addAll(list1);
		list.addAll(list2);

		list = new ArrayList<Map<String, Object>>(new LinkedHashSet<Map<String, Object>>(list));

		
		list0.add(map10);
		list0.add(map11);
		list0.add(map12);
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = list.get(i);
			for (int j = 0; j < list0.size(); j++) {
				Map<String, Object> n = list0.get(j);
				if (m.get("name").equals(n.get("name"))) {
					int id = Integer.parseInt(m.get("id")+"");
					int id1 = Integer.parseInt(n.get("id")+"");
					if (id<=id1) {
						list.add(n);
						
					}else {
						list0.remove(n);
					}
				}
			}
		}
		for(Map m:list0){
			System.out.println(m.get("id")+"="+m.get("name"));
		}
		System.out.println("---------------------------------");
		
		list.addAll(list0);
		list = new ArrayList<Map<String, Object>>(new LinkedHashSet<Map<String, Object>>(list));
		
		for(Map t:list){
			System.out.println(t.get("id")+"="+t.get("name"));
		}
	}
}