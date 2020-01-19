package com.health.jobs;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.health.utils.QiniuUtils;
@Component
public class HealthJobs {

	@Autowired
	RedisTemplate redisTemplate;
	/**
	 * 定时更新solr数据库
	 */
	@Scheduled(cron="* * 2 * * ?")
	public void shanChuQiNiuYun(){
		System.out.println("执行了任务调度"+new Date());		
		Set<String> chuan=redisTemplate.boundSetOps("chuan").members();
		Set<String> addchengguo=redisTemplate.boundSetOps("addchengguo").members();
		for (String c : chuan) {
			for (String a : addchengguo) {
				if (!c.equals(a)) {
					QiniuUtils.deleteFileFromQiniu(c);
				}
			}
			
		}
		System.out.println("成功");
	}		
}
