package com.dustin.springbootblog.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dustin.springbootblog.model.Tag;

public interface TagService {
	List<Tag> findAll();
	Optional<Tag> findById(String id);
	Optional<Tag> findByName(String name);
	Tag save(Tag Tag);
	Page<Tag> listTag(Pageable pageable);
	void deleteById(String id);
	Page<Tag> listTagTop(int i);
}
