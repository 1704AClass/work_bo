package com.health.service;

import java.util.List;

import com.health.entity.PageResult;
import com.health.pojo.TCheckitem;

public interface CheckItemService {

	public void add(TCheckitem checkitem);
	
	public void update(TCheckitem checkitem);
	
	public PageResult findPage(Integer page,Integer siez ,String queryString);
	
	public void delete(Integer id);
	
	public TCheckitem findById(Integer id);

	public List<TCheckitem> findAll();
}
