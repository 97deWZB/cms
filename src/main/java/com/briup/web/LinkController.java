package com.briup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Link;
import com.briup.exception.CustomerException;
import com.briup.service.ILinkService;
import com.briup.service.LinkServiceImpl;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Link")
//每个方法的路径都加了个/link
@Api(description = "连接相关接口")
public class LinkController {
	//web层调用service层
	@Autowired
	private ILinkService linkService;
	
	//返回的数据要统一，所以引入两个工具类
	@PutMapping("/saveOrUpdate")
	@ApiOperation("保存或者更新链接信息，如果id为空则为保存，否则为更新")
	//对连接（映射路径的说明）
	//新增用put比较好，在swagger文档里就只支持put方式
	public Message<String> saveOrUpdate(Link link){
		//传递引用型变量，将来对其说明
		linkService.saveOrUpdate(link);
		return MessageUtil.success("保存成功");
	}
	
	@GetMapping("findAll")
	@ApiOperation("查询所有的链接信息")
	public Message<List<Link>> findAll(){
		List<Link> linklist = linkService.findAll();
		return MessageUtil.success(linklist);
	}
	
	@GetMapping("/findById")
	@ApiOperation("根据id查询信息")
	@ApiImplicitParam(name="id",value="链接id",paramType="query",dataType="int",required = true)
	public Message<Link> findById(Integer id){
		Link link = linkService.findById(id);
		return MessageUtil.success(link);
	}
	
	@DeleteMapping("/deleteById")
	@ApiOperation("根据id删除一个链接")
	@ApiImplicitParam(name = "id",value = "链接id",paramType = "query",dataType="int",required=true)
	public Message<String> deleteById(Integer id){
		Message<String> message = null;
		try {
			linkService.deleteById(id);
			message = MessageUtil.success("删除成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, "该id在数据库中不存在");
		}
		return message;
	}
}
