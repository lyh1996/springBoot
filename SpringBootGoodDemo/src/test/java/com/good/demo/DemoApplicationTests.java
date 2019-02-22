package com.good.demo;

import com.good.controller.LoginController;
import com.good.controller.UserController;
import com.good.pojo.SmbmsUser;
import com.good.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
		SmbmsUser user=new SmbmsUser();
		user.setUsercode("admin");
		user.setUserpassword("1234567");
		LoginController longController=new LoginController();
		Model model;
		HttpSession session;
	}

	@Test
	public void test() {
		SmbmsUser user = new SmbmsUser();
		user.setUsercode("admin");
		user.setUserpassword("1234567");
	System.out.println(userService.loginInfo(user));
	}

}
