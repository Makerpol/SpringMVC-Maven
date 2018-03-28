package com.makerpol.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.Copies;

public interface CopiesMapper {

	int add(Copies c) throws DataAccessException ;

	int delete(Integer id) throws DataAccessException;

	int updata(Copies c) throws DataAccessException;
	
	int getCount(String param) throws DataAccessException;
	
	Copies getCopiesById(Integer id) throws DataAccessException;
	
	Copies getCopiesByName(String param) throws DataAccessException;
	
	List<Copies> getCopiesList(Map map) throws DataAccessException;
	
	List<Copies> getCopiesListByType(Map map) throws DataAccessException;
}
