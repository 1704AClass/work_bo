package com.health.mobile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.Result;
import com.health.service.SetMealService;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

	@Reference
	SetMealService mealService;
	/**
	 * 短信验证
	 * @param telephone
	 * @return
	 */
	@RequestMapping("/sendsms")
	public Result sendsms(String  telephone){
		try {
			
			mealService.sendSmsCode(telephone);
			return new Result(true, "发送成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Result(false, "发送失败");
	}
}
