package com.makerpol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.dao.NavMapper;
import com.makerpol.entity.Nav;

@Service
public class NavServiceImpl implements NavService {
	
	@Autowired
	private NavMapper mapper;
	
	@Override
	public List<Nav> getAllNav() throws DataAccessException {
		return mapper.getAll();
	}
	
	@Override
	public List<Nav> getNavByType(Integer type) {
		return mapper.getNavsByType(type);
	}

	@Override
	public Nav getNav(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nav getNav(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updataNav(Nav nav) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNav(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNav(Nav nav) throws DataAccessException {
		
		mapper.insertSelective(nav);
	}

}
