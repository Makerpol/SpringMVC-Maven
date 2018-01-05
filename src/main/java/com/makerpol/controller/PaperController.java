package com.makerpol.controller;

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

import com.makerpol.entity.Paper;
import com.makerpol.service.PaperService;

@Controller("papercontroller")
public class PaperController {
	
	@Autowired
	private PaperService service;
	
	@RequestMapping(value="/toAddPaper")
	public String toAddPaper() {
		return "/page/news/newsAdd";
	}
	
	@RequestMapping(value="/addPaper")
	@ResponseBody
	public Map<String,Object> addPaper(@RequestBody Paper paper) {
		System.out.println("papername=="+ paper.getPaperName());
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			service.addPaper(paper);
		}catch(DataAccessException e) {
			map.put("message", "添加失败！");
			System.out.println(e);
		}
		map.put("message", "添加失败！");
		return map;
	}
	
	@RequestMapping(value="/getPaper")
	@ResponseBody
	public Map<String, Object> getPaper(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Paper paper = service.getPaper(id);
			map.put("paper", paper);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		return map;
	}
	
	@RequestMapping(value="/getPaperByName")
	@ResponseBody
	public Map<String, Object> getPaperByName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Paper> list = new ArrayList<Paper>();
		try {
			list = service.getPaperByName(name);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		map.put("paperList", list);
		return map;
	}
	
	@RequestMapping(value="/toPaperList")
	public String toPaperList(Model model) {
		return "/page/news/newsList";
	}
	
	@RequestMapping(value="/toUpdataPaper")
	public String toUpdataPaper(@RequestParam Integer id, Model model) {
		try {
			System.out.println("toUpdataPaper======"+id);
			Paper paper = service.getPaper(id);
			model.addAttribute("paper", paper);
		}catch(DataAccessException e) {
			model.addAttribute("message", "error");
		}
		
		return "/page/news/newsEdit";
	}
	
	@RequestMapping(value="/getAllPaper")
	@ResponseBody
	public Map<String, Object> getAllPaper() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Paper> list = new ArrayList<Paper>();
		list = service.searchAllPaper();
		
		map.put("paperList", list);
		return map;
	}
	
	@RequestMapping(value="/upDataPaper")
	@ResponseBody
	public Map<String, Object> upDataPaper(@RequestBody Paper paper) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println("upDataPaper status == "+paper.getStatus());
			service.upDataPaper(paper);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		return map;
	}
	
	@RequestMapping(value="/deletePaper")
	@ResponseBody
	public Map deletePaper(@RequestParam Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.deletePaper(id);
		}catch(DataAccessException e) {
			map.put("message", "error");
		}
		return map;
	}
	
	
}
