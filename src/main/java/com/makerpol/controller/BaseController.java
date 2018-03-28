package com.makerpol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BaseController<T> {
	public String toListPage();
	public String toInfoPage(Integer id, Model model);
	public String toAddPage();
	public Map<String, Object> add(T t,HttpServletRequest req);
	public Map<String, Object> delete(int id);
	public Map<String, Object> update(T t);
	public Map get(Integer id);
	public Map get(String param,Integer start,Integer num);
}
