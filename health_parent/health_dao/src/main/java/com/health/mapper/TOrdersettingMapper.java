package com.health.mapper;

import com.health.pojo.TOrdersetting;
import com.health.pojo.TOrdersettingExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.annotation.Secured;

public interface TOrdersettingMapper {
    int countByExample(TOrdersettingExample example);

    int deleteByExample(TOrdersettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOrdersetting record);

    int insertSelective(TOrdersetting record);

    List<TOrdersetting> selectByExample(TOrdersettingExample example);

    TOrdersetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOrdersetting record, @Param("example") TOrdersettingExample example);

    int updateByExample(@Param("record") TOrdersetting record, @Param("example") TOrdersettingExample example);

    int updateByPrimaryKeySelective(TOrdersetting record);

    int updateByPrimaryKey(TOrdersetting record);

    @Select("select count(*) from t_ordersetting where orderDate =#{orderdate}")
	long findCountOrderSetting(@Param("orderdate")Date orderdate);

	List<TOrdersetting> getOrderSettingByMonth(Map date);

	@Select("select count(*) from t_ordersetting where orderDate = #{orderDate}")
	long findCountByOrderDate(@Param("orderdate")Date orderdate);

	void editNumberByOrderDate(TOrdersetting orderSetting);
}