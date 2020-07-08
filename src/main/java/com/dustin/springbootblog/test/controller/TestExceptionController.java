package com.dustin.springbootblog.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dustin.springbootblog.exception.UserNotExistException;

/**
 * 異常處理測試範例
 * 
 * @author dustinxie
 *
 */
@Controller
@RequestMapping("/admin")
public class TestExceptionController {

	@GetMapping("/testException/{id}")
	@ResponseBody
	public String testException(@PathVariable String id) {

		if (id.equals("1"))
			throw new UserNotExistException();

		return "success";
	}

}
