package com.makerpol.service;

import java.util.HashMap;
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
	public int addPaper(Paper paper) throws DataAccessException {
		return mapper.insertSelective(paper);
	}

	@Override
	public void deletePaper(Integer id) throws DataAccessException {
		if(mapper.checkVideo(id)!=null) {
			mapper.deleteByInnerJoin(id);
		}else {
			mapper.deleteByPrimaryKey(id);
		}
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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("param", param);
		map.put("start", 0);
		map.put("num", 13);
		map.put("order", "desc");
		return mapper.getPaperListByName(map);
	}

	@Override
	public List<Paper> getPaper(String param,Integer start,Integer num,String order) throws DataAccessException {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("param", param);
		map.put("start", start);
		map.put("num",num);
		map.put("order", order);
		return this.getPaperByName(map);
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
	
	@Override
	public int getWaitPaperCount() throws DataAccessException {
		return mapper.getWaitPaperCount();
	}

	private void setPaperClickCount(Paper paper) throws DataAccessException {
		this.upDataPaper(paper);
	}
	
	private List<Paper> getPaperByName(Map<String, Object> map) throws DataAccessException {
		String param = map.get("param").toString();
		if(param==null|| param=="") {
			return mapper.getPaperList(map);
		}
		return mapper.getPaperListByName(map);
	}
}
