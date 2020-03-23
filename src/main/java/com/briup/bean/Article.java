package com.briup.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cms_article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String author;
	private int clickTimes;
	private String content;
	private Date publishDate;
	private String title;
	
	//外键
	//多个文章对应一个栏目
	@ManyToOne
	@JoinColumn(name="category_id")
	//一个文章对应一个栏目，所以要注入category
	@JsonIgnore		//忽略转成json数据
	private Category category;
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(String author, int clickTimes, String content, Date publisDate, String title) {
		super();
		this.author = author;
		this.clickTimes = clickTimes;
		this.content = content;
		this.publishDate = publishDate;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublisDate() {
		return publishDate;
	}

	public void setPublisDate(Date publisDate) {
		this.publishDate = publisDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", clickTimes=" + clickTimes + ", content=" + content
				+ ", publisDate=" + publishDate + ", title=" + title + "]";
	}
	
	
}
