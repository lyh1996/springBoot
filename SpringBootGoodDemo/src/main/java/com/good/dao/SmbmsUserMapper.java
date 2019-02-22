package com.good.dao;


import java.util.List;

import com.good.pojo.SmbmsUser;
public interface SmbmsUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmbmsUser record);

    int insertSelective(SmbmsUser record);

    SmbmsUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmbmsUser record);

    int updateByPrimaryKey(SmbmsUser record);
    
    SmbmsUser loginInfo(SmbmsUser user);
    
    List<SmbmsUser>getAllUser(SmbmsUser user);//分页

    SmbmsUser selectByUserCode(SmbmsUser su);
   
}