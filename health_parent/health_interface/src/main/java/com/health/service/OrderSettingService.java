package com.health.service;

import java.util.List;
import java.util.Map;

import com.health.pojo.TOrdersetting;

public interface OrderSettingService {

	  public void add(List<TOrdersetting> list);
	  
	  public List<Map> getOrderSettingByMonth(String date);
	  
	  public void editNumberByDate(TOrdersetting orderSetting);
   
}
