package com.dustin.springbootblog.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String>, JpaSpecificationExecutor<Blog> {

	Page<Blog> findByRecommend(boolean b, Pageable pageable);

	@Query("select t.blogs from Tag t where t.id=:tagId")
	Page<Blog> getBlogByTagId(String tagId, Pageable pageable);

	@Query("select b from Blog b where b.title like ?1 or b.content like ?1")
	Page<Blog> searchByQuery(String query, Pageable pageable);
}
