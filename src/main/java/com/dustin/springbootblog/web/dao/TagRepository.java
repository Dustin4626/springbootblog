package com.dustin.springbootblog.web.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String>{
	
	Optional<Tag> findByName(String name);
	
	@Query("select t from Tag t")
	List<Tag> findTop(Pageable pageable);
}
