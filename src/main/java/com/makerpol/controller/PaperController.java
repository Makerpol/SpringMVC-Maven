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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makerpol.entity.Page;
import com.makerpol.entity.Paper;
import com.makerpol.service.PaperService;

/**
 * 文章管理
 * @author user
 *
 */
@Controller("papercontroller")
public class PaperController {
	
	@Autowired
	private PaperService service;
	
	/**
	 * 访问添加文章界面
	 * @return 添加文章页面
	 */
	@RequestMapping(value="/toAddPaper")
	public String toAddPaper() {
		return "/page/news/newsAdd";
	}
	
	/**
	 * 提交添加文章内容
	 * @param paper 
	 * @return 
	 */
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
	
	/**
	 * 获取指定文章内容（根据Id）
	 * @param id  文章ID
	 * @return
	 */
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
	
	/**
	 * 根据输入文章标题查询
	 * 1.输入文章标题是空或者''，则调用searchAllPaper(0,13),返回第一页文章列表
	 * 2.根据输入文章标题进行模糊查询，返回文章列表
	 * 
	 * @param name 文章标题
	 * @return 文章列表
	 */
	@RequestMapping(value="/getPaperByName")
	@ResponseBody
	public Map<String, Object> getPaperByName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Paper> list = new ArrayList<Paper>();
		try {
			list = service.getPaper(name);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		map.put("paperList", list);
		return map;
	}
	
	/**
	 * 访问文章管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toPaperList")
	public String toPaperList(Model model) {
		return "/page/news/newsList";
	}
	
	/**
	 * 访问选定的文章编辑页面
	 * @param id  选定的文章ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toUpdataPaper")
	public String toUpdataPaper(@RequestParam Integer id, Model model) {
		try {
			Paper paper = service.getPaper(id);
			model.addAttribute("paper", paper);
		}catch(DataAccessException e) {
			model.addAttribute("message", "error");
		}
		
		return "/page/news/newsEdit";
	}
	
	/**
	 * 分页查询文章列表
	 * @param start 开始行
	 * @param num	获取数
	 * @return
	 */
	@RequestMapping(value="/getAllPaper")
	@ResponseBody
	public Map<String, Object> getAllPaper(@RequestParam Integer start,@RequestParam Integer num) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Paper> list = new ArrayList<Paper>();
		Page page = new Page();
		page.setStart(start);
		page.setLimit(num);

		list = service.searchAllPaper(page);
		int total = service.getPaperCount();
		
		page.setTotal(total);
		map.put("paperList", list);
		map.put("page", page);
		return map;
	}
	
	/**
	 * 更新文章内容
	 * @param paper
	 * @return
	 */
	@RequestMapping(value="/upDataPaper")
	@ResponseBody
	public Map<String, Object> upDataPaper(@RequestBody Paper paper) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.upDataPaper(paper);
		}catch(DataAccessException e) {
			map.put("message", "error");
			System.out.println(e);
		}
		return map;
	}
	
	/**
	 * 删除文章
	 * @param id  需要删除的文章ID
	 * @return
	 */
	@RequestMapping(value="/deletePaper")
	@ResponseBody
	public Map<String, Object> deletePaper(@RequestParam Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.deletePaper(id);
		}catch(DataAccessException e) {
			map.put("message", "error");
		}
		return map;
	}
}
