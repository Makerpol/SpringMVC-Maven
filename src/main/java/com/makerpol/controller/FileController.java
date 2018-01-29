package com.makerpol.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.makerpol.Utils.FileUtil;
import com.makerpol.entity.User;
import com.makerpol.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.baidu.ueditor.ActionEnter;

@Controller("fileController")
public class FileController {
	
	@Autowired
	private UserService<?> service;
	
	/**
	 * UEditor编辑器文件上传接口：加载config.json配置文件
	 */ 
	@RequestMapping(value="config")
	public void config(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String ae = new ActionEnter( request, rootPath).exec();
		PrintWriter pw =response.getWriter();
		pw.write(ae);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 文件上传
	 * @param upfile
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam MultipartFile upfile,HttpServletRequest req){
		Map<String,Object> map = new HashMap<String,Object>();
		User user  = new User();
		user = (User)req.getSession().getAttribute("user");
		map = FileUtil.upLoad(upfile, req);
		user.setImage(map.get("URL").toString());
		service.updataUser(user);
		return map;
	} 
}
