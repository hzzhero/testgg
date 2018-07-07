package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 只处理单纯的跳转
 * @author hzz
 *
 */
@Controller
public class CommonAction {
	
	@RequestMapping("/jobHome")
	public String  jobHome() {
		return "job/jobHome";
	}

}
