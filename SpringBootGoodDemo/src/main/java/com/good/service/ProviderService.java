package com.good.service;

import com.good.pojo.SmbmsProvider;
import com.github.pagehelper.PageInfo;

public interface ProviderService {
    /**
     * 查找所有的供应商列表
     * * @param provider
     * @return
     */
    PageInfo<SmbmsProvider> getProvider(SmbmsProvider provider);

    /**
     * 根据id查找供应商信息
     * @param id
     * @return
     */
    SmbmsProvider getProviderById(Long id);

    /**
     * 更新供应商信息
     * @param provider
     * @return
     */
    int updateProvider(SmbmsProvider provider);

    /**
     * 添加供应商信息
     * @param provider
     * @return
     */
    int saveProvider(SmbmsProvider provider);

    /**
     * 删除供应商信息
     * @param id
     * @return
     */
    int deleteProvider(Long id);
}
