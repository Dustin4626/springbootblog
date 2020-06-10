package com.dustin.springbootblog.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dustin.springbootblog.model.Comment;
import com.dustin.springbootblog.web.dao.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository dao;
	
	@Override
	public List<Comment> listCommentByBlogId(String blogId) {
		return null;
	}

}
