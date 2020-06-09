package com.dustin.springbootblog.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Tag;
import com.dustin.springbootblog.model.Type;

@Repository
public interface TagRepository extends JpaRepository<Tag, String>{
	
	Optional<Tag> findByName(String name);
}
