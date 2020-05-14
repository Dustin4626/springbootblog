package com.dustin.springbootblog.web.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, BigDecimal>{
//	List<Type> findByName(String name);
	
	@Query(value="from Type where name = ?1")
	Optional<Type> findByName(String name);
}
