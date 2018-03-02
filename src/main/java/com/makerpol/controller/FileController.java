package com.makerpol.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.makerpol.Utils.FileUtil;
import com.makerpol.entity.File;
import com.makerpol.entity.User;
import com.makerpol.service.FileService;
import com.makerpol.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.baidu.ueditor.ActionEnter;

@Controller("fileController")
public class FileController {
	
	@Autowired
	private UserService<?> userservice;
	@Autowired
	private FileService fileservice;
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
		User user  = (User)req.getSession().getAttribute("LoginUser");
		map = FileUtil.upLoad(upfile, req);
		String url = replace(map.get("URL").toString());
		user.setImage(url);
		userservice.updataUser(user);
		return map;
	}
	
	@RequestMapping(value="/uploadFile")
	@ResponseBody
	public Map<String,Object> uploadFile(@RequestParam MultipartFile upfile, HttpServletRequest req){
		return FileUtil.upLoad(upfile, req);
	}
	
	@RequestMapping(value="/uploadVideo")
	@ResponseBody
	public Map<String, Object> uploadVideo(@RequestParam MultipartFile upfile, HttpServletRequest req){
		return FileUtil.upLoad(upfile, req);
	}
	
	@RequestMapping(value="/addFile")
	@ResponseBody
	public Map<String, Object> addFile(@RequestParam File file, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user  = (User)req.getSession().getAttribute("LoginUser");
		file.setUserid(user.getId());
		try {
			fileservice.addFile(file);
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/getFileById")
	@ResponseBody
	public Map<String, Object> getFileById(@RequestParam int id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			File file = fileservice.getFile(id);
			map.put("file", file);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/getFileByName")
	@ResponseBody
	public Map<String, Object> getFileByName(@RequestParam String name){
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			File file = fileservice.getFile(name);
			map.put("file", file);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map; 
	}
	
	@RequestMapping(value="/getFileList")
	@ResponseBody
	public Map<String, Object> getFileList(@RequestParam String param,@RequestParam int start,@RequestParam int num){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<File> list = fileservice.getFileList(param, start, num);
			map.put("fileList", list);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/getAllFiles")
	@ResponseBody
	public Map<String, Object> getAllFiles(@RequestParam int start, @RequestParam int num){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<File> list = fileservice.getAllFiles(start, num);
			map.put("fileList", list);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/updataFile")
	@ResponseBody
	public Map<String, Object> updataFile(@RequestParam File file){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			fileservice.updataFile(file);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/deleteFile")
	@ResponseBody
	public Map<String, Object> deleteFile(@RequestParam int id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			fileservice.deleteFile(id);
			FileUtil.remove(null);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	private String replace(String url) {
		return url.replace("\\", "/");
	}
}
