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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
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
	@RequestMapping(value="/uploadUserImage")
	@ResponseBody
	public Map<String, Object> uploadUserImage(@RequestParam MultipartFile upfile,HttpServletRequest req){
		Map<String,Object> map = new HashMap<String,Object>();
		User user  = new User();
		user = (User)req.getSession().getAttribute("LoginUser");
		map = FileUtil.upLoad(upfile, req);
		//String url = replace(map.get("URL").toString());
		
		log.debug("map     ###########",map.size());
		//user.setImage(url);
		service.updataUser(user);
		return map;
	}
	
	@RequestMapping(value="/uploadPDF")
	@ResponseBody
	public Map<String,Object> uploadPDF(@RequestParam MultipartFile upfile, HttpServletRequest req){
		return FileUtil.upLoad(upfile, req);
	}
	
	@RequestMapping(value="/uploadVideo")
	@ResponseBody
	public Map<String, Object> uploadVideo(@RequestParam MultipartFile upfile, HttpServletRequest req){
		return FileUtil.upLoad(upfile, req);
	}
	
	private String replace(String url) {
		return url.replace("\\", "/");
	}
}
