package com.health.mapper;

import com.health.pojo.TOrdersetting;
import com.health.pojo.TOrdersettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}