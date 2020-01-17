package com.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.mapper.TCheckitemMapper;
import com.health.pojo.TCheckitem;
import com.health.pojo.TCheckitemExample;
import com.health.service.CheckItemService;
@Service
public class CheckItemServiceImpl implements CheckItemService{

	@Autowired
	private TCheckitemMapper checkitemMapper;
	
	/**
	 * 添加
	 */
	@Override
	public void add(TCheckitem checkitem) {
        checkitemMapper.insert(checkitem);		
	}

	

	/**
	 * 删除
	 */
	@Override
	public void delete(Integer id) {

		checkitemMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改回现
	 */
	@Override
	public TCheckitem findById(Integer id) {
		return checkitemMapper.selectByPrimaryKey(id);
	}


  /**
   * 查询 + 分页 + 模糊
   */
	@Override
	public PageResult findPage(Integer page, Integer size, String queryString) {
		PageHelper.startPage(page, size);
		TCheckitemExample example = new TCheckitemExample();
		if (queryString!=null && !"".equals(queryString)) {
			example.createCriteria().andNameEqualTo(queryString);
		}
		Page<TCheckitem> pages =(Page<TCheckitem>) checkitemMapper.selectByExample(example);
		 
		return new PageResult(pages.getTotal(), pages.getResult());
	}



	/**
	 * 修改数据
	 */
	@Override
	public void update(TCheckitem checkitem) {
  
		checkitemMapper.updateByPrimaryKey(checkitem);
	}



	@Override
	public List<TCheckitem> findAll() {
		return checkitemMapper.selectByExample(null);
	}

}
