package com.dustin.springbootblog.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dustin.springbootblog.model.Type;
import com.dustin.springbootblog.web.dao.TypeRepository;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	TypeRepository dao;
	
	@Override
	public List<Type> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Type> findById(BigDecimal id) {
		return dao.findById(id);
	}
	
	@Transactional
	@Override
	public Type saveOrUpdateType(BigDecimal id, String name) {
		Type type = null;
		if (null == id) {
			type = new Type();
		} else {
			Optional<Type> opt = findById(id);
			type = opt.get();
		}
		type.setName(name);
		return dao.save(type);
	}
	
	@Transactional
	@Override
	public void deleteById(BigDecimal id) {
		dao.deleteById(id);
	}

}
