package com.health.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.mapper.TPermissionMapper;
import com.health.mapper.TRoleMapper;
import com.health.mapper.TUserMapper;
import com.health.pojo.TUser;
import com.health.service.UserService;
@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TPermissionMapper permissionMapper;
	@Override
	public TUser getUserName(String username) {

		 return userMapper.findByUsername(username);
	}

}
