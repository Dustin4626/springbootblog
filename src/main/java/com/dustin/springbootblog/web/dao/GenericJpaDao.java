package com.dustin.springbootblog.web.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericJpaDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	@Override
	public T findOne(ID id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	@Override
	public void create(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(ID id) {
		T findOne = findOne(id);
		delete(findOne);
	}

}
