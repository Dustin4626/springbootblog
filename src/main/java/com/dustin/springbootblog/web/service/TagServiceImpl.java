package com.dustin.springbootblog.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dustin.springbootblog.model.Tag;
import com.dustin.springbootblog.web.dao.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository dao;
	
	@Override
	public List<Tag> findAll() {
		return dao.findAll();
	}


	@Override
	public Optional<Tag> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Tag save(Tag tag) {
		return dao.save(tag);
	}

	@Override
	public Page<Tag> listTag(Pageable pageable) {
		return dao.findAll(pageable);
	}


	@Override
	public Optional<Tag> findById(String id) {
		return dao.findById(id);
	}


	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

}
