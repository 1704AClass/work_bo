package com.health.mapper;

import com.health.pojo.TSetmeal;
import com.health.pojo.TSetmealExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSetmealMapper {
    int countByExample(TSetmealExample example);

    int deleteByExample(TSetmealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSetmeal record);

    int insertSelective(TSetmeal record);

    List<TSetmeal> selectByExample(TSetmealExample example);

    TSetmeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSetmeal record, @Param("example") TSetmealExample example);

    int updateByExample(@Param("record") TSetmeal record, @Param("example") TSetmealExample example);

    int updateByPrimaryKeySelective(TSetmeal record);

    int updateByPrimaryKey(TSetmeal record);
}