package com.health.mobile.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.Result;
import com.health.pojo.TMember;
import com.health.service.MumberService;
import com.health.service.SetMealService;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

	@Reference
	SetMealService mealService;
	@Reference
	MumberService mumberService;
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
	/**
	 * 发送手机号登录
	 * @param telephone
	 * @return
	 */
	@RequestMapping("/send4Login")
	public Result send4Login(String telephone){
		try {
			
			mealService.sendSmsCode(telephone);
			return new Result(true, "发送成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Result(false, "发送失败");
	}
	
	@RequestMapping("/login")
	public Result login(@RequestBody Map map){
		String telephone=(String)map.get("telephone");
		String validateCode=(String)map.get("validateCode");
		//从redis取数据进行验证
		String getSmsCode = mealService.sgetSmsCode(telephone);
		if (getSmsCode==null || !getSmsCode.equals(validateCode)) {
			return new Result(false, "验证码错误");
		}else{
			//验证码正确
			//判断是否为会员
			TMember member = mumberService.findByTelephone(telephone);
			if (member==null) {
				//当前用户不是会员
				TMember member2 = new TMember();
				member2.setPhonenumber(telephone);
				member2.setRegtime(new Date());
			}
			//登录成功
			return new Result(true, "登录成功");
		}
	}
}
