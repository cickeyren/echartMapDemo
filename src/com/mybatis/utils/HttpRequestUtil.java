package com.mybatis.utils;  
  
import java.io.IOException;
import java.net.URLDecoder;
import net.sf.json.JSONObject; 

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;

/** 
 * Http请求工具类,发送json返回json 
 */  
public class HttpRequestUtil {
      
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);      
  
    /** 
     * 发送get请求 
     * @param url 路径 
     * @return 
     */  
    public static JSONObject httpGet(String url){  
          
        //get请求返回结果  
        JSONObject jsonResult = null;  
        try {  
            DefaultHttpClient client = new DefaultHttpClient();  
            //发送get请求  
            HttpGet request = new HttpGet(url);  
            HttpResponse response = client.execute(request);  
  
            /**请求发送成功，并得到响应**/  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                /**读取服务器返回过来的json字符串数据**/  
                String strResult = EntityUtils.toString(response.getEntity());  
                /**把json字符串转换成json对象**/  
                jsonResult = JSONObject.fromObject(strResult);  
                url = URLDecoder.decode(url, "UTF-8");  
            } else {  
                logger.error("get请求提交失败:" + url);  
            }  
        } catch (IOException e) {  
            logger.error("get请求提交失败:" + url, e);  
        }  
        return jsonResult;  
    }
    
    
    /** 
     * 无返回值
     * 发送get请求 
     * @param url 路径 
     */  
    public static void httpGet1(String url){  
          
        try {  
            DefaultHttpClient client = new DefaultHttpClient();  
            //发送get请求  
            HttpGet request = new HttpGet(url);  
            HttpResponse response = client.execute(request);  
  
            /**请求发送成功，并得到响应**/  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                /**读取服务器返回过来的json字符串数据**/  
                String strResult = EntityUtils.toString(response.getEntity());  
                /**把json字符串转换成json对象**/  
                //jsonResult = JSONObject.fromObject(strResult);  
                url = URLDecoder.decode(url, "UTF-8");  
            } else {  
                logger.error("get请求提交失败:" + url);  
            }  
        } catch (IOException e) {  
            logger.error("get请求提交失败:" + url, e);  
        }
    }
} 