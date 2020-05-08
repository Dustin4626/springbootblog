package com.dustin.springbootblog.web.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, BigDecimal>{
	List<Type> findByName(String name);
}
