package com.makerpol.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.Paper;

public interface PaperService {
	
	//添加文章
	public void addPaper(Paper paper) throws DataAccessException ;  
	//删除文章
	public void deletePaper(Integer id) throws DataAccessException ;
	//更新文章信息
	public void upDataPaper(Paper paper) throws DataAccessException ;
	//获取文章信息
	public Paper getPaper(Integer id) throws DataAccessException ;
	//获取文章总数
	public int getPaperCount(String param) throws DataAccessException ;
	
	public List<Paper> getPaper(String param) throws DataAccessException ;
	public List<Paper> getPaper(String param,Integer start, Integer num) throws DataAccessException ;
	
	public List<Paper> getPaper(String starTime,String endTime) throws DataAccessException ;
	public List<Map> getPaperTypeList() throws DataAccessException ;	
}
