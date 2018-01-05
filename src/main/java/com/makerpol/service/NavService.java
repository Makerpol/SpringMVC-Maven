package com.makerpol.service;

import java.util.List;

import com.makerpol.entity.Nav;

public interface NavService {
	public List<Nav> getAllNav();
	public Nav  getNav(Integer id);
	public Nav  getNav(String name);
	public void updataNav(Nav nav);
	public void deleteNav(Integer id);
	public void addNav(Nav nav);
}
