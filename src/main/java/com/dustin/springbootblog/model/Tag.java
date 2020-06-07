package com.dustin.springbootblog.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the t_tag database table.
 * 
 */
@Entity
@Table(name="t_tag")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	//bi-directional many-to-many association to Blog
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(
//		name="t_blog_tags"
//		, joinColumns={
//			@JoinColumn(name="tags_id")
//			}
//		, inverseJoinColumns={
//			@JoinColumn(name="blogs_id")
//			}
//		)
//	private List<Blog> blogs;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
	private List<Blog> blogs;

	public Tag() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

}