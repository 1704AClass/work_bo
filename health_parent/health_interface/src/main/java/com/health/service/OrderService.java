package com.health.service;

import java.util.Map;

import com.health.entity.Result;

public interface OrderService {

	/**
	 * 体检预约接口
	 */
	public Result order(Map map)throws Exception;
}
