package com.dustin.springbootblog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.vo.BlogQuery;
import com.dustin.springbootblog.web.service.BlogService;
import com.dustin.springbootblog.web.service.TagService;
import com.dustin.springbootblog.web.service.TypeService;

@Controller
@RequestMapping("/admin")
public class BlogController {
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/blogs")
	public String index(
			@PageableDefault(size = 5,sort = {"id"},direction = Direction.ASC) Pageable pageable,
			Model model) {
		System.out.println(pageable);
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("blogs", blogService.findAll(pageable));
		return "admin/blogs";
	}
	
	@PostMapping("/blogs/search")
	public String search(
			@PageableDefault(size = 5,sort = {"id"},direction = Direction.ASC) Pageable pageable,
			BlogQuery blogQuery, Model model) {
		System.out.println(blogQuery);
		model.addAttribute("blogs", blogService.findAll(pageable, blogQuery));
		return "admin/blogs :: blogList";
	}
	
	@GetMapping("/blogs/input")
	public String input(Model model) {
		model.addAttribute("blog", new Blog());
		model.addAttribute("types", typeService.findAll());
		return "admin/blogs-input";
	}
	
	@PostMapping("/blogs")
	public String save(Blog blog) {
		System.out.println(blog);
			blogService.save(blog);
		return "redirect:/admin/blogs";
	}
	
	@GetMapping("/blogs/{id}/input")
	public String input(@PathVariable String id , Model model) {
		Blog blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		model.addAttribute("types", typeService.findAll());
		return "admin/blogs-input";
	}
	
//	@GetMapping("/admin/blogs")
////	@ResponseBody
//	public String index(
//			@PageableDefault(size = 10,sort = {"id"},direction = Direction.ASC) Pageable pageable,
//			Model model) {
//		System.out.println(pageable);
////      int i = 9/0;
////      String blog = null;
////      if (blog == null) {
////    	  /**
////    	   *  	1.需要對應 class NotFoundException 上的annotation @ResponseStatus(HttpStatus.NOT_FOUND)
////    	   *  	
////    	   *  	2.然後 class GlobalDefaultExceptionHandler 須加上
////    	   *  	if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
////					throw e;
////				才會攔截到錯誤訊息,轉到404.html
////    	   */
////          throw  new NotFoundException("博客不存在");
////      }
//		model.addAttribute("blogs", service.findAll(pageable));
//		return "admin/blogs";
//	}
}
