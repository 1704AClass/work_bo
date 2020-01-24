package com.health.service;

import com.health.pojo.TMember;


public interface MumberService {

	public void add(TMember setmeal);
	
	public TMember findByTelephone(String telephone);
}
