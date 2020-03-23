package com.briup.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Article;
import com.briup.bean.ArticleCategory;
import com.briup.service.IArticleService;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/article")
@Api(description = "文章相关接口")
public class ArticlerController {
	//web层要调用service层。所以注入service
	@Autowired//不加会出现空指针
	private IArticleService articleservice;
	
	@PutMapping("/saveOrUpdate")
	//对swagger页面的 选项框的说明
	@ApiOperation(value = "/保存或更新一个文章",notes ="category.code,category.name可以不填")
	public Message<String> saveOrUpdate(/*Integer id,String title,String author,Integer categoryId,String content */Article article){
		Message<String> message = null;
		
		try {
			//与categoryController不同，这里新建的article里没有categoryId参数，所以有参构造器不需要变
			//这个article只有id,title,author,content,publishdate这几个类
			
	
			
	/*
	 * 
	 * 
			//web层的controller接受数据，id title author category content date
			//将收到的数据放在新建的article中
			Article article = new Article();
			article.setId(id);
			article.setTitle(title);
			article.setAuthor(author);
			article.setContent(content);
			
			article.setPublisDate(new Date());
			article.setClickTimes(0);
			
			//article中虽然注入了category类，但是并没有set getcategory类的方法
			
			//此时传递的category的id，要自己建立一个category
			Category category = new Category();
			//将	categoryId作为参数放到category类的id里
			category.setId(categoryId);
			
			
			//article中注入了category类，再把有categoryId作为id值的category设置到article中了。
			article.setCategory(category);
	*	
	*
	*
	*/
			//调用service方法，将article保存进
			articleservice.saveOrUpdate(article);
			
			message = MessageUtil.success("保存成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
	@GetMapping("/findById")
	@ApiOperation("根据id查询")
	@ApiImplicitParam(name="id",value="文章id",paramType="query",dataType="int",required = true)
	public Message<ArticleCategory> findById(int id) {
		Message<ArticleCategory> message = null;
		try {
			Article article = articleservice.findById(id);
			ArticleCategory ac = new ArticleCategory(article.getId(), article.getAuthor(),article.getClickTimes(),article.getContent(),article.getPublishDate(), article.getTitle(),article.getCategory().getName());
			
			message = MessageUtil.success(ac);
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		
		System.out.println("message is"+message.getData());
		return message;
	}
	
	@GetMapping("/findAll")
	@ApiOperation("查询所有文章信息")
	public Message<List<ArticleCategory>> findAll(){
		//返回的泛型应该是包装类
		
		List<Article> articlelist = articleservice.findAll();
		
		List<ArticleCategory> acList = new ArrayList<ArticleCategory>();
		
		for(Article a:articlelist) {
			ArticleCategory ac = new ArticleCategory(a.getId(), a.getAuthor(), a.getClickTimes(), a.getContent(), a.getPublishDate(), a.getTitle(), a.getCategory().getName());
			
			acList.add(ac);
		}
		return MessageUtil.success(acList);
	}
	
	
	@DeleteMapping("/deleteById")
	@ApiOperation("根据id删除文章")
	public Message<String> deleteById(int id) {
		Message<String> message = null;
		try {
			articleservice.deleteById(id);
			message = MessageUtil.success("删除成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
}
