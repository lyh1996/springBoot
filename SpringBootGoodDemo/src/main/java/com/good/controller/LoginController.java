package com.good.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.good.pojo.SmbmsUser;
import com.good.service.UserService;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jsp")
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(SmbmsUser user, Model mv, HttpSession session) {
//        String usercode = request.getParameter("usercode");
//        String userpassword = request.getParameter("userpassword");
        SmbmsUser loginInfo = userService.loginInfo(user);
        if (loginInfo!=null) {
            mv.addAttribute("user", user);
            session.setAttribute("user",loginInfo);
            session.setAttribute("username",loginInfo.getUsername());
            System.out.println(loginInfo);
            return "frame";
        }else {
            return "login";
        }
       
        
    }
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) throws Exception {
        // 清除 @SessionAttributes设置的session
        sessionStatus.setComplete();
        return "redirect:/login.jsp";
    }

}
