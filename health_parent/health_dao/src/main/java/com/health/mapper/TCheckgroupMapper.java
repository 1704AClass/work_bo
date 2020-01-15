package com.health.mapper;

import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckgroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCheckgroupMapper {
    int countByExample(TCheckgroupExample example);

    int deleteByExample(TCheckgroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCheckgroup record);

    int insertSelective(TCheckgroup record);

    List<TCheckgroup> selectByExample(TCheckgroupExample example);

    TCheckgroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCheckgroup record, @Param("example") TCheckgroupExample example);

    int updateByExample(@Param("record") TCheckgroup record, @Param("example") TCheckgroupExample example);

    int updateByPrimaryKeySelective(TCheckgroup record);

    int updateByPrimaryKey(TCheckgroup record);
}