package com.dustin.springbootblog.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.vo.BlogQuery;

public interface BlogService {
	List<Blog> findAll();

	Page<Blog> findAll(Pageable pageable);

	Page<Blog> findAll(Pageable pageable, BlogQuery blogQuery);

	void save(Blog blog);

	Blog getBlog(String id);
	
	void delete(String id);
}
