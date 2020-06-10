package com.dustin.springbootblog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dustin.springbootblog.web.service.BlogService;
import com.dustin.springbootblog.web.service.TagService;

@Controller
public class TagShowController {

	@Autowired
	private TagService tagService;
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/tags/{id}")
	public String tags(@PageableDefault(size = 8, sort = { "updateTime" }, direction = Direction.DESC) Pageable pageable,
			@PathVariable String id, Model model) {

		return "tags";
	}
}
