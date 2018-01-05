package com.makerpol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.dao.PaperMapper;
import com.makerpol.entity.Paper;

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
	public List<Paper> searchAllPaper() throws DataAccessException {
		return mapper.searchAllPaper();
	} 

	@Override
	public List<Paper> getPaperByName(String param) throws DataAccessException {
		return mapper.getPaperByName(param);
	}

	@Override
	public Paper getPaper(Integer id) throws DataAccessException {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Paper> getPaper(String param) throws DataAccessException {
		return mapper.getPaperList(param);
	}
	
	@Override
	public void upDataPaper(Paper paper) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(paper);
	}
}
