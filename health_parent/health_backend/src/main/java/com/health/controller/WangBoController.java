package com.health.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.pojo.TUser;
import com.health.service.WangTestSerice;

@RestController("/wangbo")
public class WangBoController {

	@Reference
	WangTestSerice wangTestSerice;
	
	@RequestMapping("/list")
	public TUser list(){
		return wangTestSerice.findAll();
	}
}
