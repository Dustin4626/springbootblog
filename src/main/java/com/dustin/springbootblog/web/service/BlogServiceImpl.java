package com.dustin.springbootblog.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dustin.springbootblog.model.Blog;
import com.dustin.springbootblog.model.Type;
import com.dustin.springbootblog.vo.BlogQuery;
import com.dustin.springbootblog.web.dao.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepository dao;
	
	@Override
	public Page<Blog> findAll(Pageable pageable) {
		Page<Blog> blogs = dao.findAll(pageable);
		return blogs;
	}

	@Override
	public List<Blog> findAll() {
		return null;
	}

	@Override
	public Page<Blog> findAll(Pageable pageable, BlogQuery blogQuery) {
		return dao.findAll(new Specification<Blog>() {

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (!StringUtils.isEmpty(blogQuery.getTitle())) {
					predicateList.add(criteriaBuilder.like(root.<String> get("title"), "%" + blogQuery.getTitle() + "%"));
				}
				if (!StringUtils.isEmpty(blogQuery.getTypeId())) {
					predicateList.add(criteriaBuilder.equal(root.<Type> get("type").get("id"), blogQuery.getTypeId()));
				}
				if (blogQuery.isRecommend()) {
					predicateList.add(criteriaBuilder.equal(root.<Boolean> get("recommend"), blogQuery.isRecommend()));
				}
				// query.where(predicates.toArray(new Predicate[predicates.size()]));
				Predicate[] pre = new Predicate[predicateList.size()];
				pre = predicateList.toArray(pre);
				return query.where(pre).getRestriction();
			}

		}, pageable);
	}

	@Override
	public void save(Blog blog) {
		dao.save(blog);
	}

}
