package com.dustin.springbootblog.web.service;

import java.util.List;

import com.dustin.springbootblog.model.Comment;

public interface CommentService {

	List<Comment> listCommentByBlogId(String blogId);
}
