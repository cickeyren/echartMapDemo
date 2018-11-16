package com.mybatis.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.mybatis.service.TestService;
import com.mybatis.utils.RtnData;
import com.mybatis.utils.StringHelper;

/**
 * 所有页面定义
 * @author rensq
 */
@Controller
@RequestMapping("/")
public class TestController {
	
	public static final Logger logger = Logger.getLogger(TestController.class);
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/assetsMap.html")
	public String assetsMap(HttpServletRequest request) {

		return "/assetsMap";
	}
	
	@RequestMapping(value = "/assetsDetail.html")
	public String assetsDetail(HttpServletRequest request) {

		return "/assetsDetail";
	}

    @RequestMapping(value="MapList",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject MapList(HttpServletRequest request) {
    	try {
    		//返回map
    		Map<String, Object> retMap = new HashMap<String, Object>();
    		//执行service
    		List<Map<String, Object>> list = testService.countCity();
			//retMap.put("list", list);
    		JSONObject jo = new JSONObject();
    		jo.put("list",list);
    		JSON.toJSONString(request.getParameterMap());
	    	return jo;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("查询失败");
			return null;
		}
    }
    
    @RequestMapping(value="MapDetail",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject MapDetail(@RequestParam(value="cityName",required = false)String cityName,HttpServletRequest request) {
    	try {
			//中文转义
    		if (!StringHelper.isEmpty(cityName)) {
    			cityName = URLDecoder.decode(cityName, "UTF-8");
			}
    		List<Map<String, Object>> noreadlist = new ArrayList<Map<String, Object>>(); 
    		noreadlist = new ArrayList<Map<String, Object>>(new HashSet<Map<String, Object>>(noreadlist));
			
    		
    		//执行service
    		List<Map<String, Object>> list = testService.listCity(URLDecoder.decode(cityName,"UTF-8"));
			//retMap.put("list", list);
    		JSONObject jo = new JSONObject();
    		jo.put("list",list);
	    	return jo;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("查询失败");
			return null;
		}
    }
    
	@RequestMapping(value = "ref", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")  
	@ResponseBody
	public String getRef(String callback,HttpServletRequest request) {
    	try {
    		//返回map
    		Map<String, Object> retMap = new HashMap<String, Object>();
    		//执行service
    		List<Map<String, Object>> list = testService.countCity();
			retMap.put("list", list);
    		return RtnData.callback(retMap,callback);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("查询失败");
			return RtnData.fail_callback("查询失败",callback);
		}
    }
}