package com.ddou.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddou.example.MybatisExample;

@Controller
public class HomeController {
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/test")
	public String index() {
		logger.error("logback error");
		MybatisExample.getCustomer();
		return "index";
	}
}
