package com.good.service;

import com.good.pojo.SmbmsUser;
import com.github.pagehelper.PageInfo;


public interface UserService {

     SmbmsUser loginInfo(SmbmsUser user);
     
     PageInfo<SmbmsUser> getAllUser(SmbmsUser user);

     int addUser(SmbmsUser su);

     SmbmsUser findUserById(Long id);

     SmbmsUser getUserByUsercode(SmbmsUser user);

     int updateUser(SmbmsUser su);

    int delUser(Long id);
}
