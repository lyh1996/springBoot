package com.good.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.dao.SmbmsProviderMapper;
import com.good.pojo.SmbmsProvider;
import com.good.service.ProviderService;
import com.github.pagehelper.PageInfo;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private SmbmsProviderMapper smbmsProviderMapper;

    @Override
    public PageInfo<SmbmsProvider> getProvider(SmbmsProvider provider) {
        // TODO Auto-generated method stub
        List<SmbmsProvider> providers = smbmsProviderMapper.getProviders(provider);
        PageInfo<SmbmsProvider> pageInfo = new PageInfo<SmbmsProvider>(providers);
        return pageInfo;
    }

    @Override
    public SmbmsProvider getProviderById(Long id) {
        SmbmsProvider sp =null;
        sp =smbmsProviderMapper.selectByPrimaryKey(id);
        return sp;
    }

    @Override
    public int updateProvider(SmbmsProvider provider) {
        return smbmsProviderMapper.updateByPrimaryKeySelective(provider);
    }

    @Override
    public int saveProvider(SmbmsProvider provider) {
        return smbmsProviderMapper.insertSelective(provider);
    }

    @Override
    public int deleteProvider(Long id) {
        return 0;
    }


}
