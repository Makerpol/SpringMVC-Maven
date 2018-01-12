package com.makerpol.controller;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller("fileController")
public class FileController {
	
	
	@RequestMapping(value="/upload")
	public String upload(@RequestParam MultipartFile[] myfile,HttpRequest request) {
		
		return "";
	} 
	
}
