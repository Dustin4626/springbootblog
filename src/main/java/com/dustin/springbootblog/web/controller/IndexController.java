package com.dustin.springbootblog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.web.service.BlogService;
import com.dustin.springbootblog.web.service.TagService;
import com.dustin.springbootblog.web.service.TypeService;

@Controller
public class IndexController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private TagService tagService;

	@GetMapping("/")
	public String index(@PageableDefault(size = 5, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("page", blogService.findAll(pageable));
		model.addAttribute("types", typeService.listTypeTop(6));
		model.addAttribute("tags", tagService.listTagTop(10));
		model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
		return "index";
	}

	@GetMapping("/blog/{id}")
	public String blog(@PathVariable String id, Model model) {
		Blog blog = blogService.getBlogByIdAndUpdateViewCount(id);
		model.addAttribute("blog", blog);
		return "blog";
	}

}
