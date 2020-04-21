package com.dustin.springbootblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	
	@GetMapping("/index")
	@ResponseBody
	public String index() {
		log.info("show index");
		return "index";
	}
	
	@GetMapping("/show")
	@ResponseBody
	public String show() {
		log.info("show req");
		int i = 1/0;
		return "show";
	}
}
