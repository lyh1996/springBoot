package com.good.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.dao.SmbmsBillMapper;
import com.good.pojo.SmbmsBill;
import com.good.service.BillService;
import com.github.pagehelper.PageInfo;
@Service
public class BillServiceImpl implements BillService{
    @Autowired
    private SmbmsBillMapper smbmsbillmapper;
    @Override
    public PageInfo<SmbmsBill> getAllBill(SmbmsBill bill) {
        // TODO Auto-generated method stub
        List<SmbmsBill> allBill = smbmsbillmapper.getAllBill(bill);
        PageInfo<SmbmsBill> pageInfo = new PageInfo<SmbmsBill>(allBill);
        return pageInfo;
    }

    @Override
    public SmbmsBill findBillById(Long billId) {

        return smbmsbillmapper.selectByPrimaryKey(billId);
    }

    @Override
    public Integer addBill(SmbmsBill bill) {
        return  smbmsbillmapper.insertSelective(bill);
    }

    @Override
    public Integer updateBill(SmbmsBill bill) {
        return smbmsbillmapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public Integer delBill(Long billId) {
        return smbmsbillmapper.deleteByPrimaryKey(billId);
    }

    @Override
    public List<SmbmsBill> getBillByProviderId(Long providerId) {
        return smbmsbillmapper.getBillByProviderId(providerId);
    }


}
