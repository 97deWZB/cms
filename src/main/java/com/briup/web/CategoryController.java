package com.briup.web;

import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Category;
import com.briup.service.ICategoryService;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/category")
@Api(description = "栏目相关接口")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("findAll")
	@ApiOperation("查询所以栏目信息")
	public Message<List<Category>> findAll(){
		List<Category> categorylist = categoryService.findAll();
		return MessageUtil.success(categorylist);
		
	}
	
	@DeleteMapping("/deleteById")
	@ApiOperation("根据id删除一个栏目")
	@ApiImplicitParam(name="id",value="栏目id",paramType="query",dataType="int",required=true)
	public Message<String> deleteById(int id){
		Message<String> message = null;
		try {
			categoryService.deleteById(id);
			message = MessageUtil.success("删除成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
	@PutMapping("/saveOrUpdate")
	@ApiOperation("保存或更新项目")
	//有级联，解决方法：让saveOrUpdate传递多个参数，在web层controller 增加一个有参构造器	
	//		保存时id没有				请求id 	有传或不传					int类型的id默认值为0，是不能与null比较的
	public Message<String> saveOrUpdate(/*@RequestParam(required = false)Integer id, String name,int code*/Category category){
		
		//System.out.println("id----------"+id);
		
		
		//能否拿到参数id  与id的类型无关，主要是参数名称是否与swagger页面里的名称一样。此处的id在service层里与null做比较了，只有Integer类型可以比较
		//原来的有参构造器Category(long code, String name){...}
		//新建的category构造器多id参数，在dao层要新建一个有 id的Category(Integer id, int code, String name){...}d的有参构造器
		//category.java没有这个（带id的catogory）的有参构造函数，这时候我需要在category.java中增加构造函数
		//Category category = new Category(id, code, name);
		//这样我们拿到了具有id code name三个参数的category  （如果是增加新数据，id是空的）
		
		Message<String> message = null;
		try {
			categoryService.saveOrUpdate(category);
			message = MessageUtil.success("保存成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
	@GetMapping("/findById")
	@ApiOperation("根据id查询一个栏目")
	@ApiImplicitParam(name="id",value="栏目id",required = true,paramType = "query",dataType = "int")
	public Message<Category> findById(int id){
		Message<Category> message = null;
		try {
			Category category = categoryService.findById(id);
			message = MessageUtil.success(category);
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
}
