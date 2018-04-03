package com.makerpol.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.makerpol.entity.User;
import com.makerpol.entity.Video;
import com.makerpol.service.UserService;
import com.makerpol.service.VideoService;

@Controller("videoController")
public class VideoController {
	@Autowired
	private VideoService service;
	private static final Logger log = LoggerFactory.getLogger(VideoController.class);
	
	/**
	 * 添加视频
	 * @param video
	 * @param req
	 */
	@RequestMapping(value="/addVideo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, Object> addVideo(@RequestBody Video video,HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = (User)req.getSession().getAttribute("LoginUser");
		log.debug(video.getIcon());
		video.setUserid(user.getId());
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
	
	@RequestMapping(value="/updateVideo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, Object> updateVideo(@RequestBody Video video,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = (User)req.getSession().getAttribute("LoginUser");
		log.debug(video.getIcon());
		video.setUserid(user.getId());
		video.setDate(sf.format(new Date()));
		try {
			service.updataVideo(video);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}
	
	@RequestMapping(value="/toVideoList")
	public String toVideoList(){
		return "/page/video/videoList";
	}
	
	@RequestMapping(value="/toVideoInfo")
	public String toVideoInfo(@RequestParam Integer id, Model model) {
		Video video = service.getVideo(id);
		model.addAttribute("video",video);
		return "/page/video/videoInfo";
	}
	
	@RequestMapping(value="/toAddVideoPage")
	public String toAddVideo() {
		return "/page/video/addVideo";
	}
	
	@RequestMapping(value="/getVideoList")
	@ResponseBody
	public Map<String, Object> getVideoList(@RequestParam String param, @RequestParam int start, @RequestParam int num){
		Map<String, Object> map = new HashMap<String,Object>();
		
		try {
			List<Map> list = service.getAllVideos(param, start, num);
			for(Map m :list) {
				
			}
			int total = service.getCount(param);
			//List<String> userNameList = service.getUserNameList(getList(list));
			//map.put("namelist",userNameList);
			map.put("videos", list);
			map.put("total", total);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.toString());
			map.put("msg", "error");
		}
		return map;
	}
	
	private List<Integer> getList(List<Video> list){
		List<Integer> namelist = new ArrayList<Integer>();
		for(Video video :list) {
			namelist.add(video.getUserid());
		}
		return namelist;
	}
	
	@RequestMapping(value="/deleteVideo")
	@ResponseBody
	public Map<String, Object> deleteVideo(@RequestParam int id,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		String path = req.getSession().getServletContext().getRealPath("/");
		try {
			service.deleteVideo(id,path);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.toString());
		}
		
		return map;
	}
}
