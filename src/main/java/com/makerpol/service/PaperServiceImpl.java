package com.makerpol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.dao.PaperMapper;
import com.makerpol.entity.Paper;

/**
 * 文章
 * @author user
 *
 */
@Service
public class PaperServiceImpl implements PaperService {
	
	@Autowired
	private PaperMapper mapper;
	
	@Override
	public void addPaper(Paper paper) throws DataAccessException {
		mapper.insertSelective(paper);
	}

	@Override
	public void deletePaper(Integer id) throws DataAccessException {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Paper getPaper(Integer id) throws DataAccessException {
		Paper paper = mapper.selectByPrimaryKey(id);
		
		paper.setClickCount(paper.getClickCount()+1);
		this.setPaperClickCount(paper);
		return paper;
	}

	@Override
	public List<Paper> getPaper(String param) throws DataAccessException {
		return mapper.getPaperListByName(param, 0, 13);
	}

	@Override
	public List<Paper> getPaper(String param,Integer start,Integer num) throws DataAccessException {
		return this.getPaperByName(param,start,num);
	}
	
	@Override
	public List<Paper> getPaper(String starTime, String endTime) throws DataAccessException {
		return mapper.getPaperListByTime(starTime,endTime);
	}

	@Override
	public void upDataPaper(Paper paper) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(paper);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getPaperTypeList() throws DataAccessException {
		return mapper.getPaperTypeList();
	}
	
	@Override
	public int getPaperCount(String param) throws DataAccessException {
		return mapper.getPaperCount(param);
	}
	
	private void setPaperClickCount(Paper paper) throws DataAccessException {
		this.upDataPaper(paper);
	}
	
	private List<Paper> getPaperByName(String param,Integer start,Integer num) throws DataAccessException {
		if(param==null|| param=="") {
			return mapper.getPaperList(0,13);
		}
		return mapper.getPaperListByName(param,start,num);
	}
}
