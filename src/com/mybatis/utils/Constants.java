package com.mybatis.utils; 

/**
 * 通用参数设置
 * @author rensq
 *
 */
public class Constants {
	
    /**
     * 通用成功代码
     */
    public final static String RTN_CODE_SUCCESS = "000000"; //通用成功代码
    /**
     * 通用错误代码
     */
    public final static String RTN_CODE_FAIL = "999999"; //通用错误代码
    public final static String RTN_CODE = "RTN_CODE"; //通用返回码名称
    public final static String RTN_MSG = "RTN_MSG"; //通用返回信息名称
    public final static String RTN_SIGN_SUCCESS = "200";
    public final static String RTN_STATUS_SUCCESS = "OK";
    public final static String RTN_STATUS_ERROR = "ERROR";

    /**
     * 通用错误信息
     */
    public final static String RTN_MESSAGE_ERROR = "请求发生异常";
  
    /**
     * 权限重构的地址
     */
    public final static String HOST_URL = PropertiesUtil.getProperty("host_url"); 
    public final static String RECONFIG_URL = HOST_URL+"/workflow/category/formServiceTest.jsp"; 
    
    /**
     * 议价时是否指定垛号
     * 是
     */
    public final static String ZD_TRUE = PropertiesUtil.getProperty("ZD_TRUE");
    
    /**
     * 议价时是否指定垛号
     * 否
     */
    public final static String ZD_FALSE = PropertiesUtil.getProperty("ZD_FALSE");

    public static String HEAD_STR = "";

    public final static String HCL_API_URL = PropertiesUtil.getProperty("hcl_api_url");
       
	public static void main(String[] args) {
		try {
			System.out.println("HCL_API_URL:" + HCL_API_URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
 
	}
}
