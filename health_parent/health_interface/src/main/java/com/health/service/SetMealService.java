package com.health.service;


import com.health.entity.PageResult;
import com.health.entity.WanNeng;
import com.health.pojo.TSetmeal;

public interface SetMealService {

	public void add(TSetmeal setmeal,Integer[] ids);
	
	public void update(TSetmeal setmeal,Integer[] ids);
	
	public PageResult findPage(Integer page,Integer siez ,String queryString);
	
	public void delete(Integer id);
	
	public WanNeng findById(Integer id);

	public void addFileName(String fileName);
	
	public void addchenggou(String fileName);
}
