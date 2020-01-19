package com.health.service;

import java.util.List;

import com.health.entity.PageResult;
import com.health.entity.WanNeng;
import com.health.pojo.TCheckgroup;

public interface CheckGroupService {

	public void add(TCheckgroup checkgroup);
	
	public void add1(TCheckgroup checkgroup,Integer[] ids);
	
	public void update(TCheckgroup checkgroup);
	
	public void update2(TCheckgroup checkgroup,Integer[] ids);
	
	public PageResult findPage(Integer page,Integer size ,String queryString);
	
	public void delete(Integer id);
	
	public WanNeng findById(Integer id);
	
	public List<TCheckgroup> findAll();
}
