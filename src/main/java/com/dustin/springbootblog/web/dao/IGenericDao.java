package com.dustin.springbootblog.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, ID extends Serializable> {

	T findOne(final ID id);

	List<T> findAll();

	void create(final T entity);

	void update(final T entity);

	void delete(final T entity);

	void deleteById(final ID entityId);
}