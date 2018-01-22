package com.makerpol.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller("fileController")
public class FileController {
	
	
	@RequestMapping(value="/upload")
	public String upload(@RequestParam MultipartFile[] myfile,HttpServletRequest req) throws IllegalStateException, IOException {
		CommonsMultipartResolver MultipartResolver= new CommonsMultipartResolver(req.getSession().getServletContext());
		String path =req.getSession().getServletContext().getRealPath("/");
		String user = req.getSession().getAttribute("user").toString();
		
		//
		if(MultipartResolver.isMultipart(req)) {
			MultipartHttpServletRequest  multiReq = (MultipartHttpServletRequest)req;
			Iterator ite = multiReq.getFileNames();
			while(ite.hasNext()) {
				MultipartFile mf = multiReq.getFile(ite.next().toString());
				
				if(mf!=null) {
					String fileName = mf.getOriginalFilename();
					mf.transferTo(new File(path+user+"/"+fileName));
				}
			}
		}
		Map map = new HashMap();
		map.put("code", 200);
		map.put("msg", "success");
		map.put("url", path+user);
		return "";
	} 
	
}
