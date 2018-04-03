package com.makerpol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.dao.CopiesMapper;
import com.makerpol.entity.Copies;

@Service
@SuppressWarnings("unchecked")
public class CopiesServiceImpl<T extends Copies> implements CopiesService<T> {
	
	@Autowired
	private CopiesMapper mapper;
	
	@Override
	public int add(T t) throws DataAccessException {
		return mapper.add(t);
	}

	@Override
	public int update(T t) throws DataAccessException {
		return mapper.updata(t);
	}

	@Override
	public int delete(Integer id) throws DataAccessException {
		return mapper.delete(id);
	}

	@Override
	public T get(Integer id) throws DataAccessException {
		return (T)mapper.getCopiesById(id);
	}

	@Override
	public T get(String name) throws DataAccessException {
		return (T)mapper.getCopiesByName(name);
	}

	@Override
	public List<T> get(Integer start, Integer num) throws DataAccessException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",start);
		map.put("num", num);
		return (List<T>) mapper.getCopiesList(map);
	}

	@Override
	public List<T> get(String param, Integer start, Integer num) throws DataAccessException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("param", param);
		map.put("start",start);
		map.put("num", num);
		return (List<T>) mapper.getCopiesList(map);
	}

	@Override
	public int getCount() throws DataAccessException {
		return mapper.getCount(null);
	}

	@Override
	public int getCount(String param) throws DataAccessException {
		if(param==null) {
			return this.getCount();
		}
		return mapper.getCount(param);
	}
}
