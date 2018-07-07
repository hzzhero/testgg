package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Test;
import com.example.demo.service.TestService;

@Controller
public class TestAction {
	
	public static Logger log=LoggerFactory.getLogger(TestAction.class);
	
	@Autowired
	private TestService testService;
	
	@ResponseBody
	@RequestMapping("/hello")
	public Object  hello() {
		log.info(new Date().getTime()+"");
		return  "hello";
	}
	
	@RequestMapping("/demo")
	public Object  demo(HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		log.info(new Date().getTime()+"");
		List<Test> list = testService.findAll();
		map.put("list", list);
		return  "demo/demo";
	}
	

}
