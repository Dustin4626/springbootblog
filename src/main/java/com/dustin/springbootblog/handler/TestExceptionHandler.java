package com.dustin.springbootblog.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dustin.springbootblog.exception.UserNotExistException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class TestExceptionHandler {

//	@ResponseBody
//	@ExceptionHandler(UserNotExistException.class)
//	public Map<String, Object> handleException(Exception e) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("code", "user.notexist");
//		map.put("message", e.getMessage());
//		return map;
//	}
	
	@ExceptionHandler(UserNotExistException.class)
	public String handleException(Exception e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		// 传入我们自己的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
		/**
		 * Integer statusCode = (Integer) request
		 * .getAttribute("javax.servlet.error.status_code");
		 */
		request.setAttribute("javax.servlet.error.status_code", 500);
		map.put("code", "user.notexist");
		map.put("message", e.getMessage());
		// 转发到/error
		return "forward:/error";
	}
}
