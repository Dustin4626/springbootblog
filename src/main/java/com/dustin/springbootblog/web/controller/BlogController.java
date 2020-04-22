package com.dustin.springbootblog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	
	@GetMapping("/index")
//	@ResponseBody
	public String index() {
		log.info("show index");
//      int i = 9/0;
//      String blog = null;
//      if (blog == null) {
//    	  /**
//    	   *  	1.需要對應 class NotFoundException 上的annotation @ResponseStatus(HttpStatus.NOT_FOUND)
//    	   *  	
//    	   *  	2.然後 class GlobalDefaultExceptionHandler 須加上
//    	   *  	if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
//					throw e;
//				才會攔截到錯誤訊息,轉到404.html
//    	   */
//          throw  new NotFoundException("博客不存在");
//      }
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
