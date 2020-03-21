package com.briup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Link;
import com.briup.dao.LinkDao;
import com.briup.exception.CustomerException;

@Service//将当前类放到spring容器中
public class LinkServiceImpl implements ILinkService{
	@Autowired
	//调用Dao
	private LinkDao linkDao;
	
	
	@Override
	public void saveOrUpdate(Link link) throws CustomerException {
		if (link!=null) {//避免空指针，不然get到空指针
			Integer id = link.getId();
			if (id==null) {
				//id为空保存内容
				linkDao.save(link);
			}else {
				//id不为空则更新
				//根据id查询link，从数据库中查询出来的
				Link link_db = linkDao.findById(id).get();
				//更新也只是更新名字或者url，此下进行对于根据id找出的判断
				if (link.getName()!=null) {
					link_db.setName(link.getName());
				}
				if (link.getUrl()!=null) {
					link_db.setUrl(link.getUrl());	
				}
				linkDao.save(link_db);
			}
		}else {
			//service层的接口实现类抛了个异常
			throw new CustomerException(500, "参数为空");
		}
	}

	@Override
	public List<Link> findAll() {
		List<Link> Linklist = linkDao.findAll();
		return Linklist;
	}

	@Override
	public Link findById(Integer id) {
		Link link = linkDao.findById(id).get();
		return link;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		//根据id查询link，如果link不存在，说明id在数据库中不存在
		Link link = linkDao.findById(id).get();
		if (link!=null) {
			linkDao.deleteById(id);
		}else {
			throw new Exception("该id在数据库中不存在");
		}
	}

}
