package com.dustin.springbootblog.web.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.dustin.springbootblog.model.Type;

public interface TypeService {
	List<Type> findAll();
	Optional<Type> findById(BigDecimal id);
	Optional<Type> findByName(String name);
	Type saveOrUpdateType(BigDecimal id, String name);
	Type save(Type type);
	void deleteById(BigDecimal id);
	String parseExcel(MultipartFile reapExcelDataFile) throws IOException;
	Page<Type> listType(Pageable pageable);
}
