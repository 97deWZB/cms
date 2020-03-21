package com.briup.service;

import java.util.List;

import com.briup.bean.Link;
import com.briup.exception.CustomerException;

public interface ILinkService {
	public void saveOrUpdate(Link link)throws CustomerException;
	//在接口的方法上面抛了个异常
	
	public List<Link> findAll();
	
	public Link findById(Integer id);
	
	public void deleteById(Integer id) throws Exception;
}
