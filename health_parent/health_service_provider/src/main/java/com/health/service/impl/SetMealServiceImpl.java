package com.health.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.WanNeng;
import com.health.mapper.TCheckitemMapper;
import com.health.mapper.TSetmealCheckgroupMapper;
import com.health.mapper.TSetmealMapper;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckitem;
import com.health.pojo.TCheckitemExample;
import com.health.pojo.TSetmeal;
import com.health.pojo.TSetmealCheckgroupExample;
import com.health.pojo.TSetmealCheckgroupKey;
import com.health.service.CheckItemService;
import com.health.service.SetMealService;
@Service
public class SetMealServiceImpl implements SetMealService{

	@Autowired
	private TCheckitemMapper checkitemMapper;
	@Autowired
	private TSetmealMapper setmealMapper;
	@Autowired
	private TSetmealCheckgroupMapper setmealCheckgroupMapper;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void add(TSetmeal setmeal, Integer[] ids) {
      
		setmealMapper.insert(setmeal);  //添加套餐管理
		System.out.println(setmeal.getId());
		setSetMealCheckGroupKey(setmeal.getId(), ids); //添加中间表
	}
	/**
	 * 添加检查组和检查项的中间表
	 * @param setMealId
	 * @param ids
	 */
	public void setSetMealCheckGroupKey(Integer setMealId,Integer[] ids){
		//System.out.println("进来");
		for (Integer id : ids) {
		//	System.out.println(id);
			TSetmealCheckgroupKey checkgroupKey = new TSetmealCheckgroupKey();
			checkgroupKey.setSetmealId(setMealId);
			checkgroupKey.setCheckgroupId(id);
		//	System.out.println(checkgroupKey);
			setmealCheckgroupMapper.insert(checkgroupKey);
		}
	}

	@Override
	public void update(TSetmeal setmeal, Integer[] ids) {
  
		setmealMapper.updateByPrimaryKey(setmeal);  //修改
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(setmeal.getId());
		setmealCheckgroupMapper.deleteByExample(example);  //删除所有id为修改的中间表
		setSetMealCheckGroupKey(setmeal.getId(), ids);//添加中间表
		
	}

	@Override
	public PageResult findPage(Integer page, Integer size, String queryString) {

		PageHelper.startPage(page, size);
		Page<TSetmeal> pages=setmealMapper.findByPage(queryString);
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	@Override
	public void delete(Integer id) {
        setmealMapper.deleteByPrimaryKey(id);// 删除
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(id);
		setmealCheckgroupMapper.deleteByExample(example);  //删除所有id为修改的中间表
		
	}

	@Override
	public WanNeng findById(Integer id) {
		TSetmeal setmeal = setmealMapper.selectByPrimaryKey(id);
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(id);
		List list1=new ArrayList();
		List<TSetmealCheckgroupKey> list = setmealCheckgroupMapper.selectByExample(example);
		for (TSetmealCheckgroupKey tSetmealCheckgroupKey : list) {
			list1.add(tSetmealCheckgroupKey.getCheckgroupId());
		}
		return new WanNeng(setmeal, list1);
	}
	@Override
	public void addFileName(String fileName) {

		redisTemplate.boundSetOps("chuan").add(fileName);
	}
	@Override
	public void addchenggou(String fileName) {
		redisTemplate.boundSetOps("addchengguo").add(fileName);

	}
	
	

}
