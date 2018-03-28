package com.makerpol.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.Paper;

public interface PaperService {
	
	public int addPaper(Paper paper) throws DataAccessException ;  
	public void deletePaper(Integer id) throws DataAccessException ;
	public void upDataPaper(Paper paper) throws DataAccessException ;
	public Paper getPaper(Integer id) throws DataAccessException ;
	public int getPaperCount(String param,List list) throws DataAccessException ;
	public int getWaitPaperCount() throws DataAccessException;
	
	public List<Paper> getPaper(String param) throws DataAccessException ;
	public List<Paper> getPaper(String param,Integer start, Integer num,String order,List column) throws DataAccessException ;
	
	public List<Paper> getPaper(String starTime,String endTime) throws DataAccessException ;
	public List<Map> getPaperTypeList() throws DataAccessException ;	
}
