package com.dustin.springbootblog.web.service;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dustin.springbootblog.exception.NotFoundException;
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
				
//				predicateList.add(criteriaBuilder.equal(root.<Boolean> get("recommend"), blogQuery.isRecommend()));
				
				// query.where(predicates.toArray(new Predicate[predicates.size()]));
				Predicate[] pre = new Predicate[predicateList.size()];
				pre = predicateList.toArray(pre);
				return query.where(pre).getRestriction();
			}

		}, pageable);
	}

	@Transactional
	@Override
	public void save(Blog blog) {
		if (StringUtils.isEmpty(blog.getId())) {
			
			//save blog
			blog.setUpdateTime(new Date());
			blog.setCreateTime(new Date());
			blog.setViews(0);
			dao.save(blog);
		} else {
			
			//update blog
			Blog dbData = getBlog(blog.getId());
			blog.setUpdateTime(new Date());
			BeanUtils.copyProperties(blog, dbData, getNullPropertyNames(blog));
			dao.save(dbData);
		}
	}

	@Override
	public Blog getBlog(String id) {
		return dao.getOne(id);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

	@Override
	public Page<Blog> listRecommendBlogTop(int i) {
		Pageable pageable = PageRequest.of(0, i, Sort.by("updateTime").descending());
		return dao.findByRecommend(true, pageable);
	}
	
	/**
	  * Returns an array of null properties of an object
	  * @param source
	  * @return
	  */
	  private String[] getNullPropertyNames (Object source) {
		  BeanWrapper beanWrapper = new BeanWrapperImpl(source);
	        PropertyDescriptor[] pds =  beanWrapper.getPropertyDescriptors();
	        List<String> nullPropertyNames = new ArrayList<>();
	        for (PropertyDescriptor pd : pds) {
	            String propertyName = pd.getName();
	            if (beanWrapper.getPropertyValue(propertyName) == null) {
	                nullPropertyNames.add(propertyName);
	            }
	        }
	        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
	  }

	@Transactional
	@Override
	public Blog getBlogByIdAndUpdateViewCount(String id) {
		Blog blog = dao.getOne(id);
		if (blog == null) {
			throw new NotFoundException("not found blog");
		}
		blog.setViews(blog.getViews() + 1);
		
		dao.save(blog);//有沒有加這行都會update blog
		
		return blog;
	}
}
