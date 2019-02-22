package com.good.service;


import java.util.List;

import com.good.pojo.SmbmsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.dao.SmbmsUserMapper;
import com.good.pojo.SmbmsUser;
import com.good.service.UserService;
import com.github.pagehelper.PageInfo;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SmbmsUserMapper smbmsUserMapper;

    @Override
    public SmbmsUser loginInfo(SmbmsUser user) {
        // TODO Auto-generated method stub
        return smbmsUserMapper.loginInfo(user);
    }
    @Override
    public PageInfo<SmbmsUser> getAllUser(SmbmsUser user) {
        // TODO Auto-generated method stub
        List<SmbmsUser> allUser = smbmsUserMapper.getAllUser(user);
        PageInfo<SmbmsUser> pageInfo = new PageInfo<SmbmsUser>(allUser);
        return pageInfo;
    }

    @Override
    public int addUser(SmbmsUser su) {
        return smbmsUserMapper.insertSelective(su);
    }

    @Override
    public SmbmsUser findUserById(Long id) {

        SmbmsUser su =null;
        su =smbmsUserMapper.selectByPrimaryKey(id);
        return su;
    }

    @Override
    public SmbmsUser getUserByUsercode(SmbmsUser user) {

        return smbmsUserMapper.selectByUserCode(user);
    }

    @Override
    public int updateUser(SmbmsUser su) {
        return smbmsUserMapper.updateByPrimaryKeySelective(su);
    }

    @Override
    public int delUser(Long id) {
        return smbmsUserMapper.deleteByPrimaryKey(id);
    }


}
