package com.good.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.pojo.SmbmsBill;
import com.good.pojo.SmbmsUser;
import com.good.service.BillService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.pojo.SmbmsProvider;
import com.good.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jsp")
public class ProviderController {

    private static final Log logger = LogFactory.getLog(ProviderController.class);

    @Autowired
    private ProviderService providerservice;

    @Autowired
    private BillService billService;

    @RequestMapping("/getProviderList")
    @ResponseBody
    public Map<String, Object> getProviderList(Integer currentPage, Integer pageSize, SmbmsProvider provider) {
        System.out.println(currentPage+"=================");
        System.out.println(pageSize+"=================");
        System.out.println(provider.toString()+"=================");
       
      if (currentPage==null && pageSize==null) {
          // 开始分页
          PageHelper.startPage(0, 0);
          PageInfo<SmbmsProvider> provider2 = providerservice.getProvider(provider);
          // 新建一个map集合用于存储数据
          Map<String, Object> map = new HashMap<String, Object>();
          // 总条数
          map.put("total", provider2.getTotal());
          // 结果集
          map.put("rows", provider2.getList());
          // 当前页的数量
          map.put("size", provider2.getSize());
          // 总页数
          map.put("pages", provider2.getPages());
          // 所有导航页号
          map.put("navigatepageNums", provider2.getNavigatepageNums());
          // 是否为首页
          map.put("isIsFirstPage", provider2.isIsFirstPage());
          // 最后一页
          map.put("isIsLastPage", provider2.isIsLastPage());
          System.err.println("供应商管理后台信息1："+map);
          return map;
    }else {
     // 开始分页
        PageHelper.startPage(currentPage, pageSize);
        PageInfo<SmbmsProvider> provider2 = providerservice.getProvider(provider);
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 总条数
        map.put("total", provider2.getTotal());
        // 结果集
        map.put("rows", provider2.getList());
        // 当前页
        map.put("currentPage", provider2.getPageNum());
        // 每页的数量
        map.put("pageSize", provider2.getPageSize());
        // 当前页的数量
        map.put("size", provider2.getSize());
        // 总页数
        map.put("pages", provider2.getPages());
        // 所有导航页号
        map.put("navigatepageNums", provider2.getNavigatepageNums());
        // 是否为首页
        map.put("isIsFirstPage", provider2.isIsFirstPage());
        // 最后一页
        map.put("isIsLastPage", provider2.isIsLastPage());
        System.out.println("供应商管理后台信息2："+map);
        return map;
    }
    }

    @RequestMapping("/provider.do")
    public String providerOperate(HttpSession session, Model model, String method, SmbmsProvider sp){
        if ("view".equals(method)) {
            logger.info("查找供应商详情");
            sp=providerservice.getProviderById(sp.getId());
            logger.info("供应商为:"+sp);
            model.addAttribute("SmbmsProvider",sp);
            return "providerview";
        } else if ("update".equals(method)) {
            logger.info("进入修改页面");

            sp=providerservice.getProviderById(sp.getId());
            logger.info("供应商为:"+sp);
            model.addAttribute("SmbmsProvider",sp);
            return "providermodify";

        } else if ("modifysave".equals(method)) {
            logger.info("保存修改供应商信息");
            SmbmsUser user=(SmbmsUser)session.getAttribute("user");
            sp.setModifyby(user.getId());//创建者
//            创建时间
            Date date=new Date();
            sp.setModifydate(date);
           logger.info("修改的供应商信息为："+sp);
            int result=providerservice.updateProvider(sp);
            if(result==1){
                return "providerlist";
            }else{
                return "result";
            }
        } else if("add".equals(method)){
            SmbmsUser user=(SmbmsUser)session.getAttribute("user");
            //创建者
            sp.setCreatedby(user.getId());
//            创建时间
            Date date=new Date();
            sp.setCreationdate(date);
            int result=providerservice.saveProvider(sp);
            if(result==1){
                return "providerlist";
            }else{
                return "result";
            }
        }
        return null;
    }

    @RequestMapping("/DeleteProvider.action")
    @ResponseBody
    public int deletionProvider(Long pid){
        List<SmbmsBill> smbmsBill =null;
        smbmsBill = billService.getBillByProviderId(pid);
        int result = 0;
        if (smbmsBill.size() > 0) {
            result = 2;
        } else {
            result= providerservice.deleteProvider(pid);
        }
        return result;
    }



}
