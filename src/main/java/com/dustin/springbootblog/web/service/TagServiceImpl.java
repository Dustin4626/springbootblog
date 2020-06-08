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
	private TagRepository service;
	
	@Override
	public List<Tag> findAll() {
		return service.findAll();
	}


	@Override
	public Optional<Tag> findByName(String name) {
		return null;
	}

	@Override
	public Tag save(Tag Tag) {
		return null;
	}

	@Override
	public Page<Tag> listTag(Pageable pageable) {
		return null;
	}


	@Override
	public Optional<Tag> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

}
