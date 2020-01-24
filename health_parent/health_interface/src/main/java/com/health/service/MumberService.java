package com.health.service;

import java.util.List;

import com.health.pojo.TMember;


public interface MumberService {

	public void add(TMember setmeal);
	
	public TMember findByTelephone(String telephone);
	
	public List<Integer> findMemberCountByMonth(List<String> moth);
}
