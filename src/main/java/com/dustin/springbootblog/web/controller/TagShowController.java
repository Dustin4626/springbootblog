package com.dustin.springbootblog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.model.Tag;
import com.dustin.springbootblog.web.service.BlogService;
import com.dustin.springbootblog.web.service.TagService;

@Controller
public class TagShowController {

	@Autowired
	private TagService tagService;
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/tags/{tagId}")
	public String tagsById(@PageableDefault(size = 8, sort = { "id" }, direction = Direction.DESC) Pageable pageable,
			@PathVariable String tagId, Model model) {
		List<Tag> tags = tagService.listTagTop(10000);
		if (tagId.equals("-1")) {
			tagId = tags.get(0).getId();
		}
		model.addAttribute("tags", tags);
		model.addAttribute("page", blogService.getBlogByTagId(tagId, pageable));
		model.addAttribute("activeTagId", tagId);
		return "tags";
	}
	
}
