package com.health.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.mapper.TOrdersettingMapper;
import com.health.pojo.TOrdersetting;
import com.health.pojo.TOrdersettingExample;
import com.health.service.OrderSettingService;

@Service
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{

	@Autowired
	private TOrdersettingMapper ordersettingMapper;

	@Override
	public void add(List<TOrdersetting> list) {

		if (list!=null && list.size()>0) {
			for (TOrdersetting tOrdersetting : list) {
				//判断此数据的日期是否存在
			    long orderSetting = ordersettingMapper.findCountOrderSetting(tOrdersetting.getOrderdate());
			    if (orderSetting>0) { //存在进行更新修改
					 ordersettingMapper.updateByPrimaryKey(tOrdersetting);
				}else{//不存在进行添加
					ordersettingMapper.insert(tOrdersetting);
				}
			}
		}
	}

	@Override
	public List<Map> getOrderSettingByMonth(String date) {
 
		String dateBegin=date+"-1"; //2019-3-1
		String dateEnd=date+"-31"; //2019-3-31
		Map map=new HashMap();
		map.put("dateBegin", dateBegin);
		map.put("dateEnd", dateEnd);
		
		List<TOrdersetting> list = ordersettingMapper.getOrderSettingByMonth(map);
        List<Map> data=new ArrayList<>();
        for (TOrdersetting ordersetting : list) {
			Map orderSettingMap=new HashMap<>();
			orderSettingMap.put("data", ordersetting.getOrderdate().getDate());
			orderSettingMap.put("number", ordersetting.getNumber());
			orderSettingMap.put("reservations", ordersetting.getReservations());
			data.add(orderSettingMap);
		}
		return data;
	}

	@Override
	public void editNumberByDate(TOrdersetting orderSetting) {

		long count = ordersettingMapper.findCountByOrderDate(orderSetting.getOrderdate());
		if (count>0) {
			//当前日期已经进行了预约设置
			ordersettingMapper.editNumberByOrderDate(orderSetting);
		}else{
			ordersettingMapper.insert(orderSetting);
		}
		
	}
	


}
