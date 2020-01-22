package com.health.mapper;

import com.health.pojo.TOrder;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(TOrder TOrder);
    public List<TOrder> findByCondition(TOrder TOrder);
    public Map findById4Detail(Integer id);
    public Integer findTOrderCountByDate(String date);
    public Integer findTOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();
}
