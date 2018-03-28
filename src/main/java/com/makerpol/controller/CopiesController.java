package com.makerpol.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makerpol.entity.Copies;
import com.makerpol.service.CopiesService;

@Controller("copiescontroller")
public class CopiesController<T extends Copies> implements BaseController<T> {
	@Autowired
	private CopiesService service;
	private static final Logger log = LoggerFactory.getLogger(CopiesController.class);
	
	@Override
	@RequestMapping("/toCopiesListPage")
	public String toListPage() {
		return "/page/copies/copiesList";
	}

	@Override
	@RequestMapping("/toCopiesInfoPage")
	public String toInfoPage(@RequestParam Integer id, Model model) {
		Copies copies = (Copies) service.get(id);
		model.addAttribute("copies",copies);
		return "/page/copies/copiesInfo";
	}

	@Override
	@RequestMapping("/toCopiesAddPage")
	
	public String toAddPage() {
		return "/page/copies/copiesAdd";
	}

	@Override
	@RequestMapping(value="/addCopies")
	@ResponseBody
	public Map<String, Object> add(@RequestBody T t, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			service.add(t);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}

	@Override
	@RequestMapping(value="/deleteCopies")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.delete(id);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}

	@Override
	@RequestMapping(value="/updateCopies")
	@ResponseBody
	public Map<String, Object> update(@RequestBody T t) {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			service.update(t);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}

	@Override
	@RequestMapping(value="/getCopies")
	@ResponseBody
	public Map<String,Object> get(@RequestParam Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Copies copies = (Copies)service.get(id);
			map.put("cpoies", copies);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}

	@Override
	@RequestMapping(value="/getCopiesList")
	@ResponseBody
	public Map<String,Object> get(@RequestParam String param,@RequestParam Integer start,@RequestParam Integer num) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<Copies> list = service.get(param, start, num);
			int total = service.getCount(param);
			map.put("total", total);
			map.put("copiesList", list);
			map.put("msg", "success");
		}catch(DataAccessException e) {
			log.error(e.getMessage());
			map.put("msg", "error");
		}
		return map;
	}

}
