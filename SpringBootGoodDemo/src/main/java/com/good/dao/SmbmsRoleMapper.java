package com.good.dao;


import com.good.pojo.SmbmsRole;

import java.util.List;

public interface SmbmsRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmbmsRole record);

    int insertSelective(SmbmsRole record);

    SmbmsRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmbmsRole record);

    int updateByPrimaryKey(SmbmsRole record);

    List<SmbmsRole> listSmbmsRole();
}