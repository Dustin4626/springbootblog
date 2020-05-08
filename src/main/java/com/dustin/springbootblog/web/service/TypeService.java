package com.dustin.springbootblog.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.dustin.springbootblog.model.Type;

public interface TypeService {
	List<Type> findAll();
	Optional<Type> findById(BigDecimal id);
	Type saveOrUpdateType(BigDecimal id, String name);
	void deleteById(BigDecimal id);
}
