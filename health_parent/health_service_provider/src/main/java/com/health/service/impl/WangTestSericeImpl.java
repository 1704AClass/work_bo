package com.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.mapper.TUserMapper;
import com.health.pojo.TUser;
import com.health.service.WangTestSerice;
@Service
public class WangTestSericeImpl implements WangTestSerice{

	@Autowired 
	TUserMapper userMapper;
	@Override
	public TUser findAll() {
		return userMapper.selectByExample(null).get(0);
	}

}
