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
 * rest api call get mehtod testException/{id}
 * ->throw new "UserNotExistException"->"TestExceptionHandler" catch exception
 * ->request.setAttribute status_code 5xx or 4xx
 * ->可以存放HashMap錯誤訊息至request Attribute
 * ->then forward:/error
 * 
 * ps:forward:/error will handler by "BasicErrorController(spring officail)",
 * it will judge request is by html or other like rest api,
 * and forward to JSON/HTML;
 * 
 * ->extends DefaultErrorAttributes ->"MyErrorAttributes"(for stored customize error message)
 * ->WebRequest get Attribute error message object and stored in super.getErrorAttributes
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
