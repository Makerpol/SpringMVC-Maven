package com.makerpol.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makerpol.Utils.MD5Util;
import com.makerpol.entity.User;
import com.makerpol.service.UserService;

/**
 * 用户管理
 * @author user
 *
 */
@Controller("userController")
public class UserController {
	
	@Autowired
	private UserService<?> service;
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchUser")
	public String searchUser(Integer id, Model model) {
		User user = (User)service.getUser(id);
		model.addAttribute("user",user);
		return "/page/user/userInfo";
	}
	
	@RequestMapping(value="/searchUserByLike")
	@ResponseBody
	public Map<String,Object> searchUserByLike(@RequestParam String param) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<User> list = new ArrayList<User>();
		list = service.getUserList(param);
		
		if(list.size()==0) {
			map.put("message", "没有符合的数据！");
		}

		map.put("userList", list);
		return map;
	}
	
	@RequestMapping(value="/userList")
	public String userList(Model model) {
		List<User> list = new ArrayList<User>();
		list = service.getAllUser();
		model.addAttribute("userList", list);
		return "/page/user/userList";
	}
	
	@RequestMapping(value="/changePsw")
	public String changePsw(@RequestParam Integer id, Model model) {
		User user = service.getUser(id);
		model.addAttribute("user",user);
		return "/page/user/changePwd";
	}
	
	@RequestMapping(value="/toUpdataUser")
	public String toUpdataUser(@RequestParam Integer id, Model model) {
		User user = service.getUser(id);
		model.addAttribute("user",user);
		return "/page/user/userInfo";
	}
	
	@RequestMapping(value="/updataUser", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<Object,Object> updataUser(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Map<Object,Object> map = new HashMap<Object,Object>();
		//MD5加密
		user.setPassword(MD5Util.EncoderByMd5(user.getPassword()));
		try {
			service.updataUser(user);
		}catch(DataAccessException e) {
			System.out.println(e);
			map.put("message", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public Map<Object,Object> deleteUser(@RequestParam Integer id) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			service.deleteUser(id);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		
		return map;
	}
	
	@RequestMapping(value="/deleteUserList")
	public Map<Object, Object> deleteUserList(@RequestParam List<Integer> list) {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			for(int i=0;i<list.size();i++) {
				service.deleteUser(list.get(i));
			}
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		
		return map;		
	}
	
	@RequestMapping(value="/toAddUser")
	public String toAddUser() {
		return "/page/user/addUser";
	}
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<Object,Object> addUser(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Map<Object,Object> map = new HashMap<Object,Object>();
		//MD5加密
		user.setPassword(MD5Util.EncoderByMd5(user.getPassword()));
		try {
			service.addUser(user);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		return map;
	}
}
