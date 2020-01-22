package com.health.mapper;

import com.health.pojo.TOrdersetting;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void add(TOrdersetting TOrdersetting);
    //更新可预约人数
    public void editNumberByOrderDate(TOrdersetting TOrdersetting);
    //更新已预约人数
    public void editReservationsByOrderDate(TOrdersetting TOrdersetting);
    public long findCountByOrderDate(Date orderDate);
    //根据日期范围查询预约设置信息
    public List<TOrdersetting> getTOrdersettingByMonth(Map date);
    //根据预约日期查询预约设置信息
    public TOrdersetting findByOrderDate(Date orderDate);
}
