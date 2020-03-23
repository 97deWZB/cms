package com.briup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Category;
import com.briup.dao.CategoryDao;
@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> findAll() {
		List<Category> catelist = categoryDao.findAll();
		return catelist;
	}

	@Override
	public void deleteById(int id) throws Exception {
		//判断id是否存在，根据id查询栏目信息，如果该栏目为空id不存在，否则存在
		Optional<Category> opt = categoryDao.findById(id);
		Category category = opt.isPresent()?opt.get():null;
		if (category!=null) {
			categoryDao.deleteById(id);
		}else {
			throw new Exception("该id在数据库中不存在");
		}
	}

	@Override
	public void saveOrUpdate(Category category) throws Exception {
		// 先判断id是否为空，避免空指针异常
		if (category!=null) {
			Integer id = category.getId();
			//id要与null比较，不能是int类型，int默认值为0，无法与null比较
			//id为空保存
			if (id==null) {
				categoryDao.save(category);
			}else {
				{
					//根据id从数据库查询出对应的category
					Category category_db = categoryDao.findById(id).get();
					String name = category.getName();
					long code = category.getCode();
					if (name!=null) {
						category_db.setName(name);
					}
					if (code!=0) {
						category_db.setCode(code);
					}
					categoryDao.save(category_db);
				}
			}
		}else {
			throw new Exception("参数为空");
		}
		
	}

	@Override
	public Category findById(int id) throws Exception {
		Optional<Category> opt = categoryDao.findById(id);
		Category category = opt.isPresent()?opt.get():null;
		if (category!=null) {
			return category;
		}else {
			throw new Exception("id在数据库在不存在");
		}
	
	}

}
