package com.dustin.springbootblog.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>{

}
