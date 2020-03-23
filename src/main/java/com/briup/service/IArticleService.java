package com.briup.service;

import java.util.List;

import org.jvnet.fastinfoset.VocabularyApplicationData;

import com.briup.bean.Article;

public interface IArticleService {
	public void saveOrUpdate(Article article) throws Exception;
	
	public Article findById(int id) throws Exception;
	
	public List<Article> findAll();
	
	public void deleteById(int id) throws Exception;
	//要删除的可能不存在，所有抛异常
}
