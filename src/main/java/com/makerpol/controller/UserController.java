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
	 * 访问编辑用户信息页面
	 * @param id  用户ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchUser")
	public String searchUser(Integer id, Model model) {
		User user = (User)service.getUser(id);
		model.addAttribute("user",user);
		return "/page/user/userInfo";
	}
	
	/**
	 * 访问用户管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userList")
	public String userList() {
		return "/page/user/userList";
	}
	
	/**
	 * 获取用户列表（分页查询）
	 * @param start  开始行
	 * @param num	  查询数
	 * @return
	 */
	@RequestMapping(value="/getUserList")
	@ResponseBody
	public Map<String, Object> getUserList(@RequestParam String param ,@RequestParam int start, @RequestParam int num) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> list = new ArrayList<User>();

		list = service.getUserList(param,start, num);
		int total = service.getCount(param);
		map.put("userList", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 访问修改密码页面
	 * @param id  用户ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/changePsw")
	public String changePsw(@RequestParam Integer id, Model model) {
		User user = service.getUser(id);
		model.addAttribute("user",user);
		return "/page/user/changePwd";
	}
	
	/**
	 * 访问用户信息编辑页面
	 * @param id  用户ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toUpdataUser")
	public String toUpdataUser(@RequestParam Integer id, Model model) {
		User user = service.getUser(id);
		model.addAttribute("user",user);
		return "/page/user/userInfo";
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
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
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 批量删除用户
	 * @param list
	 * @return
	 */
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
	
	/**
	 * 访问添加用户页面
	 * @return
	 */
	@RequestMapping(value="/toAddUser")
	public String toAddUser() {
		return "/page/user/addUser";
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
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
