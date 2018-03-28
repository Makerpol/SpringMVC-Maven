package com.makerpol.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BaseService<T> {
	public int add(T t) throws DataAccessException;
	
	public int update(T t) throws DataAccessException;
	
	public int delete(Integer id) throws DataAccessException;
	
	public T get(Integer id) throws DataAccessException;
	
	public T get(String name) throws DataAccessException;
	
	public List<T> get(Integer start, Integer num) throws DataAccessException;
	
	public List<T> get(String param, Integer start, Integer num) throws DataAccessException;
	
	public int getCount() throws DataAccessException;
	
	public int getCount(String param) throws DataAccessException;
}
