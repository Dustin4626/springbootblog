package com.dustin.springbootblog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dustin.springbootblog.web.service.BlogService;
import com.dustin.springbootblog.web.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BlogService blogService;
	
	@Value("${comment.avatar}")
	private String avatar;
	
	@GetMapping("/comments/{blogId}")
	public String tags(@PathVariable String blogId,Model model) {
		model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
		return "tags";
	}
}
