package com.health.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.WanNeng;
import com.health.mapper.TCheckgroupCheckitemMapper;
import com.health.mapper.TCheckgroupMapper;
import com.health.mapper.TCheckitemMapper;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckgroupCheckitemExample;
import com.health.pojo.TCheckgroupCheckitemKey;
import com.health.pojo.TCheckgroupExample;
import com.health.pojo.TCheckitem;
import com.health.pojo.TCheckitemExample;
import com.health.service.CheckGroupService;
import com.health.service.CheckItemService;
@Service
public class CheckGroupServiceImpl implements CheckGroupService{

	@Autowired
	private TCheckgroupMapper checkgroupMapper;
	@Autowired
	private TCheckitemMapper checkitemMapper;
	@Autowired
	private TCheckgroupCheckitemMapper checkgroupCheckitemMapper;

	/**
	 * 检查组添加
	 */
	@Override
	public void add(TCheckgroup checkgroup) {

		checkgroupMapper.insert(checkgroup);
	}

	/**
	 * 检查组修改
	 */
	@Override
	public void update(TCheckgroup checkgroup) {

		checkgroupMapper.updateByPrimaryKey(checkgroup);
	}

	/**
	 * 查询 + 分页 +模糊
	 */
	@Override
	public PageResult findPage(Integer page, Integer size, String queryString) {
  
		PageHelper.startPage(page, size);
		
		Page<TCheckgroup> pages=(Page<TCheckgroup>) checkgroupMapper.findPageAndEQ(queryString);
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	/**
	 * 检查组删除
	 */
	@Override
	public void delete(Integer id) {

		checkgroupMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 检查组修改回现一个对象
	 */
	@Override
	public WanNeng findById(Integer id) {
         TCheckgroup selectByPrimaryKey = checkgroupMapper.selectByPrimaryKey(id);
         //查询一个分组的所有检测项
         TCheckgroupCheckitemExample example = new TCheckgroupCheckitemExample();
         example.createCriteria().andCheckgroupIdEqualTo(id);
         //查询中间表所有数据
         List<TCheckgroupCheckitemKey> selectByExample = checkgroupCheckitemMapper.selectByExample(example);
         List list=new ArrayList();
         for (TCheckgroupCheckitemKey tCheckgroupCheckitemKey : selectByExample) {
        	 list.add(tCheckgroupCheckitemKey.getCheckitemId());
		}
         
		return new WanNeng(selectByPrimaryKey, list);
	}

	@Override
	public void add1(TCheckgroup checkgroup, Integer[] ids) {
		checkgroupMapper.insert(checkgroup);
		setCheckGroupAndCheckItem(checkgroup.getId(), ids);
	}
	
	/**
	 * 设置检查组和检查项的中间表的关系
	 * 
	 */
	public void setCheckGroupAndCheckItem(Integer gid,Integer[] ids){
		for (Integer id : ids) {
			TCheckgroupCheckitemKey checkitemKey = new TCheckgroupCheckitemKey();
			checkitemKey.setCheckgroupId(gid);
			checkitemKey.setCheckitemId(id);
			checkgroupCheckitemMapper.insert(checkitemKey);
		}
		
	}
	
	

}
