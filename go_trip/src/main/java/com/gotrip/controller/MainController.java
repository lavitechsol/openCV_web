package com.gotrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/index1")
	public String showDefaultPage1() {
		
		return "index1";
	}
	
	@RequestMapping()
	public String showDefaultPage2() {
		
		return "index2";
	}
	
	
}
