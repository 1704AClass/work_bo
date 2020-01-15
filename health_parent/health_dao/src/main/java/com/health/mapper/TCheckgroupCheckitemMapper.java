package com.health.mapper;

import com.health.pojo.TCheckgroupCheckitemExample;
import com.health.pojo.TCheckgroupCheckitemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCheckgroupCheckitemMapper {
    int countByExample(TCheckgroupCheckitemExample example);

    int deleteByExample(TCheckgroupCheckitemExample example);

    int deleteByPrimaryKey(TCheckgroupCheckitemKey key);

    int insert(TCheckgroupCheckitemKey record);

    int insertSelective(TCheckgroupCheckitemKey record);

    List<TCheckgroupCheckitemKey> selectByExample(TCheckgroupCheckitemExample example);

    int updateByExampleSelective(@Param("record") TCheckgroupCheckitemKey record, @Param("example") TCheckgroupCheckitemExample example);

    int updateByExample(@Param("record") TCheckgroupCheckitemKey record, @Param("example") TCheckgroupCheckitemExample example);
}