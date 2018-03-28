package com.makerpol.dao;

import org.springframework.dao.DataAccessException;


public interface BaseMapper<T>{
	int add(T t) throws DataAccessException ;
	int delete(Integer id) throws DataAccessException ;
	int updata(T t) throws DataAccessException ;
	
	
	
}
