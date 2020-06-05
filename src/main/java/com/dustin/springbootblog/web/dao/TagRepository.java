package com.dustin.springbootblog.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String>{
}
