package com.makerpol.service;

import java.util.List;

import com.makerpol.entity.Paper;

public interface PaperService {
	
	//添加文章
	public void addPaper(Paper paper);  
	//删除文章
	public void deletePaper(Integer id);
	//更新文章信息
	public void upDataPaper(Paper paper);
	//获取文章信息
	public Paper getPaper(Integer id);
	//获取文章总数
	public int getPaperCount(String param);
	
	public List<Paper> getPaper(String param);
	public List<Paper> getPaper(String param,Integer start, Integer num);
	
	public List<Paper> getPaper(String starTime,String endTime);
	public List<List> getPaperTypeList();	
}
