package com.health.mapper;

import com.github.pagehelper.Page;
import com.health.pojo.TMember;

import java.util.List;

public interface MemberDao {
    public List<TMember> findAll();
    public Page<TMember> selectByCondition(String queryString);
    public void add(TMember TMember);
    public void deleteById(Integer id);
    public TMember findById(Integer id);
    public TMember findByTelephone(String telephone);
    public void edit(TMember TMember);
    public Integer findTMemberCountBeforeDate(String date);
    public Integer findTMemberCountByDate(String date);
    public Integer findTMemberCountAfterDate(String date);
    public Integer findTMemberTotalCount();
}
