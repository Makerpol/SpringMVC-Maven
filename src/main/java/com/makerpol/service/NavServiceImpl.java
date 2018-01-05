package com.makerpol.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.entity.Nav;
@Service
public class NavServiceImpl implements NavService {

	@Override
	public List<Nav> getAllNav() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

}
