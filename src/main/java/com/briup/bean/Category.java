package com.briup.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name ="cms_category")
@ApiModel
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="栏目id")
	private Integer id;
	@ApiModelProperty(value="栏目编码")
	private long code;
	@ApiModelProperty(value="栏目名称")
	private String name;
	
//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//	private List<Article> articles;
	
	public Category(long code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	
//	//新建的带id的构造器
	public Category(Integer id, long code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public Category() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(List<Article> articles) {
//		this.articles = articles;
//	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	
	
}
