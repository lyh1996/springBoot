package com.good.service;

import com.good.pojo.SmbmsBill;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BillService {
    /**
    获取所有的订单信息
     */
    PageInfo<SmbmsBill>getAllBill(SmbmsBill bill);
    /**
    通过id查找订单信息
     */
    SmbmsBill findBillById(Long billId);
    /**
    添加订单信息
     */
    Integer  addBill(SmbmsBill bill);
    /**
    更新订单信息
     */
    Integer updateBill(SmbmsBill bill);
    /**
    删除订单
     */
    Integer delBill(Long billId);

    /**
     * 根据供应商查找信息
     */
    List<SmbmsBill> getBillByProviderId(Long providerId);


}
