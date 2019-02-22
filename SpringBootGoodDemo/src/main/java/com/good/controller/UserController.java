package com.good.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.pojo.SmbmsBill;
import com.good.pojo.SmbmsRole;
import com.good.service.RoleInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.pojo.SmbmsUser;
import com.good.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jsp")
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleInfoService roleInfoService;

    @ResponseBody
    @RequestMapping("/getUser")
    public Map<String, Object> getBillList(Integer pageNum, Integer pageSize, SmbmsUser user) {
        System.out.println(pageNum+"=================");
        System.out.println(pageSize+"=================");
        System.out.println(user.toString()+"=================");
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SmbmsUser> allUser = userService.getAllUser(user);
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 总条数
        map.put("total", allUser.getTotal());
        // 结果集
        map.put("rows", allUser.getList());
        // 当前页
        map.put("pageNum", allUser.getPageNum());
        // 每页的数量
        map.put("pageSize", allUser.getPageSize());
        // 当前页的数量
        map.put("size", allUser.getSize());
        // 总页数
        map.put("pages", allUser.getPages());
        // 所有导航页号
        map.put("navigatepageNums", allUser.getNavigatepageNums());
        // 是否为首页
        map.put("isIsFirstPage", allUser.isIsFirstPage());
        // 最后一页
        map.put("isIsLastPage", allUser.isIsLastPage());
        System.out.println("开始遍历商品");
        System.err.println("订单管理后台信息："+map);
        return map;

    }

    @RequestMapping("/getAllRoleInfo.action")
    public @ResponseBody Map<String, Object> getAllRoleInfo() {
        logger.info("获取所有的角色信息");
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        List<SmbmsRole> listRoleInfo = roleInfoService.listRoleInfo();
        map.put("RoleInfo",listRoleInfo);

        return map;
    }

    @RequestMapping("/user.do")
    public String userOperate(HttpSession session, Model model, String method, SmbmsUser su ,String userBirthday,
                              String newpassword){
        if("view".equals(method)){
            su=userService.findUserById(su.getId());
            model.addAttribute("SmbmsUser",su);
            return "userview";

        }else if("add".equals(method)){
            SmbmsUser user=(SmbmsUser)session.getAttribute("user");
            su.setCreatedby(user.getId());//创建者
//            创建时间
            Date date=new Date();
            su.setCreationdate(date);
            Date birthday = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                birthday = simpleDateFormat.parse(userBirthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            su.setBirthday(birthday);
            logger.info("添加的用户信息为："+su);
            int result=userService.addUser(su);
            if(result==1){
                return "userlist";
            }else{
                return "result";
            }
        } else if("update".equals(method)){
            su=userService.findUserById(su.getId());
            System.out.println("用户信息"+su);
            model.addAttribute("SmbmsUser",su);
            return "usermodify";
        }else if("modifyexe".equals(method)){
            SmbmsUser user=(SmbmsUser)session.getAttribute("user");
            su.setModifyby(user.getId());//创建者
//            创建时间
            Date date=new Date();
            su.setModifydate(date);
            Date birthday = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                birthday = simpleDateFormat.parse(userBirthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            su.setBirthday(birthday);
            int result=userService.updateUser(su);
            if(result==1){
                return "userlist";
            }else{
                return "result";
            }
        } else if ("savepwd".equals(method)) {
            SmbmsUser user=(SmbmsUser)session.getAttribute("user");
            user.setUserpassword(newpassword);
            Date date=new Date();
            user.setModifydate(date);
            user.setModifyby(user.getId());
            logger.info("用户信息"+user);
            int result = userService.updateUser(user);
            if (result == 1) {
                return "login";
            } else {
                return "result";
            }

        }
        return null;
    }

    @RequestMapping("/getUserByUserCode.do")
    @ResponseBody
    public String getUserInfoByUserCode(SmbmsUser su) {
    SmbmsUser user = null;
    user = userService.getUserByUsercode(su);
    if(user == null) {
        return "1";
    } else {
        return "0";
    }
    }

    @RequestMapping("/deleteUser.action")
    @ResponseBody
    public int deletionUser(Long id){
        int result= userService.delUser(id);

        return result;
    }

    @RequestMapping("/pwdModified.action")
    @ResponseBody
    public Map<String,String> pwdModified(HttpSession session, String oldpassword) {
        Map<String, String> map = new HashMap<>();
        SmbmsUser user=(SmbmsUser)session.getAttribute("user");
        if (user == null) {
            map.put("result", "sessionerror");

        }else if (oldpassword == null || "".equals(oldpassword)){
            map.put("result", "error");
        } else {
            user.setUserpassword(oldpassword);
            SmbmsUser user1 = userService.loginInfo(user);
            if (user1 == null) {
                map.put("result", "false");
            } else {
                map.put("result", "true");
            }
        }
        return map;

    }
    
}
