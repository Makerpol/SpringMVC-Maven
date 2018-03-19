package com.makerpol.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makerpol.Utils.EmailUtil;
import com.makerpol.Utils.MD5Util;
import com.makerpol.Utils.VerifyCodeUtil;
import com.makerpol.common.Common;
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
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	/**
	 * 访问编辑用户信息页面
	 * @param id  用户ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchUser")
	public String searchUser(Integer id, Model model) throws DataAccessException{
		User user = service.getUser(id);
		model.addAttribute("User",user);
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
	public Map<String, Object> getUserList(@RequestParam String param ,@RequestParam int start, @RequestParam int num) 
			throws DataAccessException{
		
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
	public String changePsw(@RequestParam Integer id, Model model) throws DataAccessException{
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
	public String toUpdataUser(@RequestParam Integer id, Model model) throws DataAccessException{
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
	public Map<Object,Object> updataUser(@RequestBody User user) throws Exception {
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		if(user.getPassword() != null) {
			//MD5加密
			user.setPassword(MD5Util.EncoderByMd5(user.getPassword()));
		}
		
		try {
			service.updataUser(user);
		}catch(DataAccessException e) {
			map.put("message", "error");
			throw e;
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
	public Map<Object,Object> deleteUser(@RequestParam Integer id) throws DataAccessException{
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			service.deleteUser(id);
		}catch(DataAccessException e) {
			map.put("message", "error");
			throw e;
		}
		
		return map;
	}
	
	/**
	 * 批量删除用户
	 * @param list
	 * @return
	 */
	@RequestMapping(value="/deleteUserList")
	public Map<Object, Object> deleteUserList(@RequestParam List<Integer> list) throws DataAccessException{
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			for(int i=0;i<list.size();i++) {
				service.deleteUser(list.get(i));
			}
		}catch(DataAccessException e) {
			map.put("message", "error");
			throw e;
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
	public Map<Object,Object> addUser(@RequestBody User user, HttpServletRequest req) throws Exception{
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		try {
			//MD5加密
			user.setPassword(MD5Util.EncoderByMd5(Common.DF_PASSWORD));
			user.setImage(Common.DF_IMAGES);
			service.addUser(user);
		}catch(DataAccessException |NoSuchAlgorithmException | UnsupportedEncodingException e) {
			map.put("message", "error");
			log.error(e.toString());
		}
		return map;
	}
	
	/**
	 * 登录名验证
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/checkName")
	@ResponseBody
	public Map<String, Object> checkLoginName(@RequestParam String name) throws DataAccessException{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = service.getUser(name);
		if(user == null) {
			map.put("msg", "no");
		}else {
			map.put("msg", "existed");
		}
		
		return map;
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/reSetPassword")
	@ResponseBody
	public Map<String,String> reSetPassword(Integer id) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		User user = new User();
		
		try {
			user.setId(id);
			user.setPassword(MD5Util.EncoderByMd5(Common.DF_PASSWORD));
			service.updataUser(user);
			map.put("msg", "success");
		} catch (DataAccessException|NoSuchAlgorithmException | UnsupportedEncodingException e) {
			map.put("msg", "error");
			throw e;
		}
		
		return map;
	}
	
	@RequestMapping(value="/sendVerifyCode")
	@ResponseBody
	public Map<String, Object> sendVerifyCode(@RequestParam String userMail,HttpServletRequest req) throws AddressException, MessagingException {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = service.getUserByEmail(userMail);
		if(user.equals(null)) {
			map.put("msg", "error");
			return map;
		}
		
		String verifyCode = VerifyCodeUtil.getVerifyCode();
		EmailUtil.mailSend(user, "验证码", verifyCode);
		
		req.getSession().setAttribute("verifyUser", user);
		req.getSession().setMaxInactiveInterval(360);
		map.put("verifyCode", verifyCode);
		map.put("msg", "success");
		return map;
	}
	
	@RequestMapping(value="/toReSetPwd")
	public String toReSetPwd(HttpServletRequest req, Model model) {
		return "/page/user/reSetPwd";
	}
	
	@RequestMapping(value="/checkVerifyCode")
	@ResponseBody
	public Map<String, Object> checkVerifyCode(@RequestParam String code,HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String verifyCode = req.getSession().getAttribute("verifyCode").toString();
		
		if(code.equals(verifyCode)) {
			map.put("msg", "success");
		}else {
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/getUserAnaData")
	@ResponseBody
	public Map<String, Object> getUserAnaData(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	
	@RequestMapping(value="/getUserCount")
	@ResponseBody
	public Map<String,Object> getCount(){
		Map<String, Object> map = new HashMap<String,Object>();
		
		try {
			int total = service.getCount(null);
			map.put("total", total);
			map.put("msg","success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/toEmailVerifyPage")
	public String toEmailVerifyPage() {
		
		return "";
	}
}
