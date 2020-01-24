package com.health.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.WanNeng;
import com.health.mapper.TCheckgroupCheckitemMapper;
import com.health.mapper.TCheckgroupMapper;
import com.health.mapper.TCheckitemMapper;
import com.health.mapper.TMemberMapper;
import com.health.mapper.TSetmealCheckgroupMapper;
import com.health.mapper.TSetmealMapper;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckgroupCheckitemExample;
import com.health.pojo.TCheckgroupCheckitemKey;
import com.health.pojo.TCheckgroupExample;
import com.health.pojo.TCheckitem;
import com.health.pojo.TCheckitemExample;
import com.health.pojo.TMember;
import com.health.pojo.TMemberExample;
import com.health.pojo.TSetmeal;
import com.health.pojo.TSetmealCheckgroupExample;
import com.health.pojo.TSetmealCheckgroupKey;
import com.health.service.CheckItemService;
import com.health.service.MumberService;
import com.health.service.SetMealService;
import com.health.utils.MD5Utils;
import com.health.utils.SMSUtils;
import com.health.utils.ValidateCodeUtils;
@Service
public class MumberServiceImpl implements MumberService{

	
	@Autowired
	private TMemberMapper memberMapper;
	

	@Override
	public TMember findByTelephone(String telephone) {

		TMember a=memberMapper.findByTelephone(telephone);
		if (a==null) {
			return new TMember();
		}else{
			return a;
		}
		
	}


	@Override
	public void add(TMember member) {
		if (member.getPassword()!=null) {
			member.setPassword(MD5Utils.md5(member.getPassword()));
		}
		memberMapper.insert(member);
	}


	@Override
	public List<Integer> findMemberCountByMonth(List<String> moth) {

		List<Integer> list = new ArrayList<>();
		for (String m : moth) {
			m =m+".31";//格式：2019.04.31
			Integer count=memberMapper.findMemberCountByMonth(m);
			list.add(count);
		}
		return list;
	}
	
	

}
