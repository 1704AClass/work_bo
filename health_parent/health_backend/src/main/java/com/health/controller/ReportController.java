package com.health.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.Result;
import com.health.service.MumberService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Reference
	private MumberService mumberService;
	
	/**
	 * 会员数量统计
	 */
	@RequestMapping("/getMemberReport")
	private Result getMemberReport(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -12);
		List<String> list = new ArrayList<>();
		for (int i = 0; i <12; i++) {
			calendar.add(Calendar.MONTH, 1);
			list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
			
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("months", list);
		List<Integer> memberCount=mumberService.findMemberCountByMonth(list);
		map.put("memberCount", memberCount);
		return new Result(true, "成功", map);
	}
}
