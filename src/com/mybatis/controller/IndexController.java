package com.mybatis.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所有页面定义
 * @author rensq
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	public static final Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value = "/index1.html")
	public String index1(HttpServletRequest request) {

		return "/index1";
	}
	@RequestMapping(value = "/index2.html")
	public String index2(HttpServletRequest request) {

		return "/index2";
	}
	@RequestMapping(value = "/index3.html")
	public String index3(HttpServletRequest request) {

		return "/index3";
	}
	@RequestMapping(value = "/index4.html")
	public String index4(HttpServletRequest request) {

		return "/index4";
	}
	
	@RequestMapping(value = "/index5.html")
	public String index5(HttpServletRequest request) {

		return "/index5";
	}
	@RequestMapping(value = "/index6.html")
	public String index6(HttpServletRequest request) {

		return "/index6";
	}
}