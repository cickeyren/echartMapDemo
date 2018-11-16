package com.mybatis.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:华平电子水分监测API接口 
 * @author rensq
 * @date 2018-8-17
 */
public class HclApi {
   
	public static final Logger logger = LoggerFactory.getLogger(HclApi.class);
	
    public static RtnData<Object> getHclByParams(String carno,String limit,String startime,String endtime,String nodeid){
    	try {
    		Map<String,Object> ret = new HashMap<String, Object>();
    		String hcl = "";
    		double hclsum = 0.0;
    		//从配置文件中获取api_url
    	    String api_url = PropertiesUtil.getProperty("hcl_api_url")+"getdata";
    	    //String startime = "";
    	    //String endtime = "";
    		//权限重构的地址url
    	    //String url = api_url+"?carno="+carno+"&startime="+startime+"&endtime="+endtime+"&limit="+limit;
    	    if (StringHelper.isEmpty(carno)) {
    	    	return RtnData.fail("读取回潮率失败,车牌号不能为空");
			}else {
				api_url += "?carno="+carno;
				if (!StringHelper.isEmpty(startime)){
					api_url += "&startime="+DateUtil.DateTimeToSpeStr(startime);
				}
				if (!StringHelper.isEmpty(endtime)){
					api_url += "&endtime="+DateUtil.DateTimeToSpeStr(endtime);
				}
				if (!StringHelper.isEmpty(limit)){
					api_url += "&limit="+limit;
				}
				if (!StringHelper.isEmpty(nodeid)){
					api_url += "&nodeid="+nodeid;
				}
			}
    	    //发送请求
    		//参数carno,startime,endtime,limit
    		JSONObject jsonResult = HttpRequestUtil.httpGet(api_url);
    		System.out.println(jsonResult.toString());
    		//返回码 0-参数无错，1-nodeId不存在 ;2- startime格式错误;3- endtime格式错误;4-limit参数错误
        	String error = jsonResult.get("error").toString();
        	//0-访问接口失败，1接口访问正常
        	String status = jsonResult.get("status").toString();
			if ("0".equals(status)) {
				if ("1".equals(error)) {
					return RtnData.fail("读取回潮率失败,设备号不存在");
				}else {
					return RtnData.fail("读取回潮率失败,参数错误");
				}
			}else {
	    		if ("0".equals(error)) {
	    			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonResult.get("data");
	    			if (dataList.size()>0) {
	    				for (int i = 0; i < dataList.size(); i++) {
	    					Map<String, Object> map = dataList.get(i);
	    				    hcl += map.get("ndwatr").toString()+",";
	    				    hclsum += Double.parseDouble(map.get("ndwatr").toString());
						}	
		    			if (!StringHelper.isEmpty(hcl)) {
		    				hcl = hcl.substring(0, hcl.length() - 1);
						}
		    			ret.put("hcl", hcl);
		    			ret.put("hclavg",DoubleUtil.formatDouble5(hclsum/dataList.size()));
		        		return RtnData.ok(ret);
					}else {
						return RtnData.fail("未检测到回潮率,请重新获取");
					}

				}else if ("1".equals(error)) {
					return RtnData.fail("读取回潮率失败,设备号不存在");
				}else {
					return RtnData.fail("读取回潮率失败,参数错误");
				}
			}
			//return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("回潮率接口访问失败");
            return null;
        }
    }
    
    /**
     * 绑定车牌
     * @param carno
     * @param nodeid
     * @return
     */
    public static RtnData<Object> bandingCarNo(String carno,String nodeid){
    	try {
    		Map<String,Object> ret = new HashMap<String, Object>();
    		//从配置文件中获取api_url
    	    String api_url = PropertiesUtil.getProperty("hcl_api_url")+"banding";
    		//http://mingyu-sz.ticp.net:11570/kuaiyike/jf/wx/ajax/banding?nodeid=201605503&carno=川S.AT097
    	    if (StringHelper.isEmpty(carno) || StringHelper.isEmpty(nodeid)) {
    	    	return RtnData.fail("绑定车牌失败,参数不能为空");
			}else {
				api_url += "?carno="+carno;
				api_url += "&nodeid="+nodeid;
			}
    	    //发送请求
    		//参数carno,nodeid
    		JSONObject jsonResult = HttpRequestUtil.httpGet(api_url);
    		System.out.println(jsonResult.toString());
    		//error 返回码 0-参数无错，1-nodeId不存在 ;
        	String error = jsonResult.get("error").toString();
        	//statu	0-访问接口失败，1接口访问正常
        	String status = jsonResult.get("status").toString();
			if ("0".equals(status)) {
				if ("1".equals(error)) {
					return RtnData.fail("绑定车牌失败,设备号不存在");
				}else {
					return RtnData.fail("绑定车牌失败,参数错误");
				}
			}else {
	    		if ("0".equals(error)) {
	    			//data 返回说明 0:绑定失败,1:成功 
	    			if (!StringHelper.isEmpty(jsonResult.get("data"))) {
	    				String data = jsonResult.get("data").toString();
	    				if ("1".equals(data)) {
	    					return RtnData.ok("绑定成功");
	    				}else {
	    					return RtnData.fail("绑定失败,请联系管理员");
						}
					}else {
						return RtnData.fail("绑定失败,请联系管理员");
					}

				}else if ("1".equals(error)) {
					return RtnData.fail("绑定车牌失败,设备号不存在");
				}else {
					return RtnData.fail("绑定车牌失败,参数错误");
				}
			}
			//return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("绑定车牌失败");
            return null;
        }
    }
    
	public static void main(String[] args) {
		//RtnData rt = getHclByParams("川s.at099", "10", "2017-08-16 10:22:00", "","201605503");
		RtnData rt = bandingCarNo("川S.AT097", "111");
		System.out.println(rt.getResult());
		System.out.println(rt.getMessage());
		//System.out.println(jsonResult.get("error"));
	}
}
