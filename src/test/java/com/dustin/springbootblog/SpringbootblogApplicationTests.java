package com.dustin.springbootblog;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dustin.springbootblog.model.Comment;
import com.dustin.springbootblog.web.service.CommentService;

@SpringBootTest
class SpringbootblogApplicationTests {

	@Autowired
	private CommentService service;
	
	@Test
	void contextLoads() {
		List<Comment> listCommentByBlogId = service.listCommentByBlogId("6");
		for(Comment comment :listCommentByBlogId) {
			System.out.println(comment);
		}
	}

}
