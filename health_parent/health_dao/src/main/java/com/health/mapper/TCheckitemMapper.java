package com.health.mapper;

import com.health.pojo.TCheckitem;
import com.health.pojo.TCheckitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCheckitemMapper {
    int countByExample(TCheckitemExample example);

    int deleteByExample(TCheckitemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCheckitem record);

    int insertSelective(TCheckitem record);

    List<TCheckitem> selectByExample(TCheckitemExample example);

    TCheckitem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCheckitem record, @Param("example") TCheckitemExample example);

    int updateByExample(@Param("record") TCheckitem record, @Param("example") TCheckitemExample example);

    int updateByPrimaryKeySelective(TCheckitem record);

    int updateByPrimaryKey(TCheckitem record);
}