package com.briup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotation.Adapt;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.Category;
import com.briup.dao.ArticleDao;

@Service  //不注入service，则启动控制类时会报空指针异常
public class ArticleServiceImpl implements IArticleService{
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public void saveOrUpdate(Article article) throws Exception {
		if (article!=null) {
			Integer id = article.getId();
			if (id==null) {
				//直接保存
				articleDao.save(article);
			}else {
				//更新
				//根究id查询出要修改的文章
				//从数据库找出对应id的数据
				Article article_db = articleDao.findById(id).get();
				String author = article.getAuthor();
				String title = article.getTitle();
				Category category = article.getCategory();
				if (article!=null) {
					article_db.setAuthor(author);
				}
				if (title!=null) {
					article_db.setTitle(title);
				}
				if (author!=null) {
					article_db.setAuthor(author);
				}
				if (category.getId()!=null) {
					//这个category是自己在controller里创建的，不会为空，
					//但在swaggger里填的id是categotyId,不填的话可以取到空值
					//所以用category.getId()
					article.setCategory(category);
				}
				articleDao.save(article_db);
			}
		}else {
			throw new Exception();
		}
	}

	@Override
	public Article findById(int id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Article> opt = articleDao.findById(id);
		
		//判断id是否存在，存在则用article接收opt，不然则返回空值
		Article article = opt.isPresent()?opt.get():null;
		if (article!=null) {
			//拿到点击次数，在此基础上上加1，放入article
			article.setClickTimes(article.getClickTimes()+1);
			//保存跟新了点击次数的article
			articleDao.save(article);
			System.out.println("article is :"+article);
			System.out.println("category--------:"+article.getCategory());
			return article;
		}else {
			throw new Exception("该id在数据库中不存在");
		}
		
	}

	@Override
	public List<Article> findAll() {
		List<Article> articlelist = articleDao.findAll();
		return articlelist;
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		Optional<Article> opt = articleDao.findById(id);
		Article article = opt.isPresent()?opt.get():null;
		if (article!=null) {
			articleDao.deleteById(id);
		}else {
			throw new Exception("该id在数据库中不存在");
		}
	}
	
	
}
