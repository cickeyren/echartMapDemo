package com.mybatis.utils;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * json工具类
 * 
 * @author rensq
 * 
 */
public class JsonUtil {

	/**
	 * json转xml
	 * 
	 * @param json
	 * @param rootName
	 * @return
	 * @throws DocumentException
	 */
	public static String json2Xml(JSONObject json, String rootName)
			throws DocumentException {

		String sXml = "";
		XMLSerializer xmlSerializer = new XMLSerializer();
		xmlSerializer.setTypeHintsEnabled(false);
		xmlSerializer.setRootName(rootName);
		// String sContent = xmlSerializer.write(json);
		String sContent = xmlSerializer.write(json, "gb2312");
		try {
			Document docCon = DocumentHelper.parseText(sContent);
			sXml = docCon.getRootElement().asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return sXml;
	}

	/**
	 * 将实体转换为xml
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertObj2XML(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		XMLSerializer xmlSerializer = new XMLSerializer();
		xmlSerializer.setTypeHintsEnabled(false);
		xmlSerializer.setRootName("root");
		String xml = "";
		try {
			xml = DocumentHelper
					.parseText(xmlSerializer.write(jsonObject, "GB2312"))
					.getRootElement().asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		return xml;
	}

	public static void main(String[] args) {
		try {
			// String jsonStr =
			// "{\"id\": \"a94dbe57c52e448b8ba6f4aced78f60f\",\"name\": \"renshiqi\",\"test\": \"测试测试测试\"}";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Fbxrxm", "王老师");
			jsonObject.put("id", 0);
			System.out.println(json2Xml(jsonObject, "root"));

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}