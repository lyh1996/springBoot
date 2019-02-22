package com.good.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.good.pojo.SmbmsUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.pojo.SmbmsBill;
import com.good.service.BillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jsp")
public class BillController {

    private static final Log logger = LogFactory.getLog(BillController.class);

    @Autowired
    private BillService billService;

    @RequestMapping("/getBillList")
    @ResponseBody
    public Map<String, Object> getBillList(Integer pageNum, Integer pageSize, SmbmsBill bill) {
        System.out.println(pageNum+"=================");
        System.out.println(pageSize+"=================");
        System.out.println(bill.toString()+"=================");
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SmbmsBill> allBill = billService.getAllBill(bill);
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 总条数
        map.put("total", allBill.getTotal());
        // 结果集
        map.put("rows", allBill.getList());
        // 当前页
        map.put("pageNum", allBill.getPageNum());
        // 每页的数量
        map.put("pageSize", allBill.getPageSize());
        // 当前页的数量
        map.put("size", allBill.getSize());
        // 总页数
        map.put("pages", allBill.getPages());
        // 所有导航页号
        map.put("navigatepageNums", allBill.getNavigatepageNums());
        // 是否为首页
        map.put("isIsFirstPage", allBill.isIsFirstPage());
        // 最后一页
        map.put("isIsLastPage", allBill.isIsLastPage());
        System.out.println("开始遍历商品");
        System.err.println("订单管理后台信息："+map);
        return map;

    }

    @RequestMapping("/bill.do")
    public String billOperate(HttpSession session,Model model, String method, SmbmsBill sb){
        if("view".equals(method)){
            //SmbmsBill sb=new SmbmsBill();
            sb=billService.findBillById(sb.getId());
            System.out.println(sb);
            model.addAttribute("SmbmBill",sb);
           return "billview";
        }else if("add".equals(method)){
            SmbmsUser user=(SmbmsUser)session.getAttribute("user");
            sb.setCreatedby(user.getId());//创建者
//            创建时间
            Date date=new Date();
            sb.setCreationdate(date);
          int result=billService.addBill(sb);
          if(result==1){
              return "billlist";
          }else{
              return "result";
          }
        }else if("update".equals(method)){
            sb=billService.findBillById(sb.getId());
            System.out.println("订单"+sb);
            model.addAttribute("SmbmBill",sb);
            return "billmodify";
        }else if("modifysave".equals(method)){
                SmbmsUser user=(SmbmsUser)session.getAttribute("user");
                sb.setModifyby(user.getId());//创建者
//            创建时间
                Date date=new Date();
                sb.setModifydate(date);
                System.out.println("更新信息"+sb);
                int result=billService.updateBill(sb);
                if(result==1){
                    return "billlist";
                }else{
                    return "result";
                }
        }
        return null;
    }

    @RequestMapping("/deletion.action")
    @ResponseBody
    public int deletionBill(Long billid){
        int result= billService.delBill(billid);

        return result;
    }

}
