package com.makerpol.controller;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.makerpol.entity.User;
import com.makerpol.service.UserService;
/**
 * 
 * @author user
 *
 */
@Controller("loginController")
public class LoginController {
	
	@Autowired
	private UserService<?> service;
	
	/**
	 * 登录处理
	 * @param username 登录用户名
	 * @param password 登录密码
	 * @param model		
	 * @param session
	 * @return	
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value="/userLogin", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, Model model,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		User user = service.getUser(username);
		
		if(user==null||user.getPassword()==null||!user.getPassword().equals(password)) {
			model.addAttribute("LoginMessige", "用户名或密码错误！");
			return "login";
		}
		
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		return "/page/index";
	}
	
	/*
	 * 注销处理
	 */
	@RequestMapping(value="/userLogout")
	public String userLogout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
}
