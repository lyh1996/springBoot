package com.good.service;

import com.good.dao.SmbmsRoleMapper;
import com.good.pojo.SmbmsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2018-11-06 9:35
 * @since 1.7
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    SmbmsRoleMapper smbmsRoleMapper;

    @Override
    public List<SmbmsRole> listRoleInfo() {
        return smbmsRoleMapper.listSmbmsRole();
    }
}
