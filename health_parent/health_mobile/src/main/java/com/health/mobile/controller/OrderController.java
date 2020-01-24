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
	public Result submitOeder(@RequestBody Map map) throws Exception{
		String telephone=(String)map.get("telephone");
		//System.out.println(telephone+"yz");
		if(telephone==null && "".equals(telephone)){
			return new Result(false, "请输入手机号");
		}
		String getRedisCode = setMealService.sgetSmsCode(telephone);
		String validateCode =(String)map.get("validateCode");
		if (getRedisCode==null) {
			//验证码已经失效
			return new Result(false, "验证码失效");
		}
		
		//System.out.println("取出来"+getRedisCode);
		if (validateCode ==null&&!telephone.equals(getRedisCode)) {
			//验证码不正确
			return new Result(false, "验证码不正确");
		}
		
			 map.put("orderType", "微信预约");
			 return orderService.order(map);
		
		
			//预约成功发给短信通知用户
		
		
		
	}
}
