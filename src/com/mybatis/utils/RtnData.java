package com.mybatis.utils;

import net.sf.json.JSONObject;


/**
 * 通用json数据返回类型
 * @author rensq
 *
 * @param <T>
 */
public class RtnData<T> {

    private String code = Constants.RTN_CODE_SUCCESS;
    private String message = "success";
    private T result;
    private String status = Constants.RTN_STATUS_SUCCESS;

    public static RtnData ok(Object result){
        RtnData rtnData = new RtnData();
        rtnData.setCode(Constants.RTN_CODE_SUCCESS);
        rtnData.setStatus(Constants.RTN_STATUS_SUCCESS);
        rtnData.setResult(result);
        
        return rtnData;
    }

    public static RtnData ok(Object result,String message){
        RtnData rtnData = new RtnData();
        rtnData.setCode(Constants.RTN_CODE_SUCCESS);
        rtnData.setStatus(Constants.RTN_STATUS_SUCCESS);
        rtnData.setResult(result);
        rtnData.setMessage(message);
        return rtnData;
    }
    
    public static RtnData success(String message){
        RtnData rtnData = new RtnData();
        rtnData.setCode(Constants.RTN_CODE_SUCCESS);
        rtnData.setStatus(Constants.RTN_STATUS_SUCCESS);
        rtnData.setMessage(message);
        return rtnData;
    }
    
    public static RtnData fail(String result){
        RtnData rtnData = new RtnData();
        rtnData.setCode(Constants.RTN_CODE_FAIL);
        rtnData.setStatus(Constants.RTN_STATUS_ERROR);
        rtnData.setMessage(result);
        return rtnData;
    }

    public static RtnData fail(Object result){
        return fail(result, null);
    }

    public static RtnData fail(Object result, String code, String message){
        RtnData rtnData = new RtnData();
        rtnData.setCode(code);
        rtnData.setMessage(message);
        rtnData.setStatus(Constants.RTN_STATUS_ERROR);
        rtnData.setResult(result);
        return rtnData;
    }

    public static String callback(Object result,String callback){
    	RtnData rtnData = new RtnData();
	    rtnData.setCode(Constants.RTN_CODE_SUCCESS);
	    rtnData.setStatus(Constants.RTN_STATUS_SUCCESS);
	    rtnData.setResult(result);
	    JSONObject json = JSONObject.fromObject(rtnData);  
	    String jsonStr = json.toString();
	    jsonStr  = callback + "(" + jsonStr + ")";
	    return jsonStr;

    }
    
    public static String fail_callback(String message,String callback){
    	RtnData rtnData = new RtnData();
	    rtnData.setCode(Constants.RTN_CODE_FAIL);
	    rtnData.setStatus(Constants.RTN_STATUS_ERROR);
	    rtnData.setMessage(message);
	    JSONObject json = JSONObject.fromObject(rtnData);  
	    String jsonStr = json.toString();
	    jsonStr  = callback + "(" + jsonStr + ")";
	    return jsonStr;

    }
    public static String backCrossDomain(Object result){
    	RtnData rtnData = new RtnData();
	    rtnData.setCode(Constants.RTN_CODE_SUCCESS);
	    rtnData.setStatus(Constants.RTN_STATUS_SUCCESS);
	    rtnData.setResult(result);
	    JSONObject json = JSONObject.fromObject(rtnData);  
	    String jsonStr = json.toString();
	    return jsonStr;

    }
    
    public static RtnData fail(Object result, String message){
        return fail(result, Constants.RTN_CODE_FAIL, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
