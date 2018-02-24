package com.makerpol.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makerpol.entity.User;
import com.makerpol.entity.Video;
import com.makerpol.service.VideoService;

@Controller("videoController")
public class VideoController {
	@Autowired
	private VideoService service;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 添加视频
	 * @param video
	 * @param req
	 */
	@RequestMapping(value="/addVideo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, Object> addVideo(@RequestBody Video video,HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		User user = (User)req.getSession().getAttribute("LoginUser");
		video.setPaperid(user.getId());
		video.setDate(sf.format(new Date()));
		
		try {
			service.addVideo(video);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.toString());
			map.put("msg", "error");
		}
		return map;
	}
	
	
	public Map<String, Object> deleteVideo(int id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.deleteVideo(id);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.toString());
		}
		
		return map;
	}
	
	
	
	
	
}