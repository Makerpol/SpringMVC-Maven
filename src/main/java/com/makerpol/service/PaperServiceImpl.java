package com.makerpol.service;

import java.util.List;

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
	public List<Paper> searchAllPaper() throws DataAccessException {
		return mapper.searchAllPaper();
	} 

	@Override
	public Paper getPaper(Integer id) throws DataAccessException {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Paper> getPaper(String param) throws DataAccessException {
		return this.getPaperByName(param);
	}
	
	@Override
	public List<Paper> getPaper(String starTime, String endTime) {
		return mapper.getPaperListByTime(starTime,endTime);
	}

	@Override
	public void upDataPaper(Paper paper) throws DataAccessException {
		paper.setText(format(paper.getText()));
		mapper.updateByPrimaryKeySelective(paper);
	}
	
	@Override
	public List<Paper> getPaperListByType(Integer type) {
		return mapper.getPapeListrByType(type);
	}

	private List<Paper> getPaperByName(String param) throws DataAccessException {
		return mapper.getPaperListByName(param);
	}

	/**
	 * 替换文章内容中的换行(\r),空格(" ")，保证文章格式不变
	 * @param text 文章内容
	 * @return format后的文章内容
	 */
	private String format(String text) {
		return text.replaceAll("\r", "<br>").replaceAll(" ","&nbsp;&nbsp;");
	}
}
