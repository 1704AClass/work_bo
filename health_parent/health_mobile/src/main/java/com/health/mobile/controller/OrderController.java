package com.health.mobile.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.Result;
import com.health.service.OrderService;
import com.health.service.SetMealService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Reference
	OrderService orderService;
	@Reference
	SetMealService setMealService;
	
	@RequestMapping("/submit")
	public Result submitOeder(@RequestBody Map map){
		String telephone=(String)map.get("telephone");
		String getRedisCode = setMealService.sgetSmsCode(telephone);
		if (getRedisCode==null) {
			//验证码已经失效
			return new Result(false, "验证码失效");
		}
		if (!telephone.equals(getRedisCode)) {
			//验证码不正确
			return new Result(false, "验证码不正确");
		}
		
		Result result=null;
		try {
			map.put("orderType", "微信预约");
			 result = orderService.order(map);
		} catch (Exception e) {
			// TODO: handle exception
			return result;
		}
		
		if (result.isFlag()) {
			//预约成功发给短信通知用户
			
		}
		return result;
		
		
	}
}
