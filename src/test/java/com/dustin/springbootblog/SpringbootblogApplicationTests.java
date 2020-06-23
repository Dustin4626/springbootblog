package com.dustin.springbootblog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.model.Comment;
import com.dustin.springbootblog.web.dao.BlogRepository;

@SpringBootTest
class SpringbootblogApplicationTests {
	
	@Autowired
	BlogRepository dao;
	
	@Test
	@Transactional
	public void test(){
		Blog blog = dao.findById("6").get();
		
		String id = null;
		for(Comment comment:blog.getComments()) {
			
			Comment parentComment = comment.getParentComment();
//			if (StringUtils.isEmpty(parentComment)) {
//				id = comment.getId();
//				System.out.println(comment.getContent());
//			} else {
//				System.out.println(parentComment.getContent());
//			}
//			System.out.println();
		}
		
//		Blog one = dao.getOne("6");
//		System.out.println(blog);
	}
}
