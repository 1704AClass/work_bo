package com.health.mapper;

import com.health.pojo.TMember;
import com.health.pojo.TMemberExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TMemberMapper {
    int countByExample(TMemberExample example);

    int deleteByExample(TMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMember record);

    int insertSelective(TMember record);

    List<TMember> selectByExample(TMemberExample example);

    TMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByExample(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByPrimaryKeySelective(TMember record);

    int updateByPrimaryKey(TMember record);

    @Select("select * from t_member where phoneNumber=#{telephone}")
	TMember findByTelephone(@Param("telephone")String telephone);

    @Select("select count(id) from t_member where regTime &lt;= #{value}")
	Integer findMemberCountByMonth(String m);
}