package com.dustin.springbootblog.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WebConfig設定檔註冊兩個測試攔截器
 * addInterceptors
 * 
 * FirstInterceptor.java
 * SecondInterceptor.java
 * @author dustinxie
 *
 */
@Controller
@RequestMapping("/admin")
public class TestInterceptorController {

	@GetMapping("/testInterceptor")
	public String testInterceptor() {
		return "admin/testInterceptor";
	}
}
