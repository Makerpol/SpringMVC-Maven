package com.makerpol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.dao.UserMapper;
import com.makerpol.entity.User;

@Service
public class UserServiceImpl<T> implements UserService<T> {
	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public List<User> getUserList(String param,Integer start, Integer num) throws DataAccessException {
		if(param==null || param=="") {
			return this.getAllUser(0,13);
		}
		return mapper.getUserList(param,start,num);
	}

	@Override
	public User getUser(Integer id) throws DataAccessException {
		return mapper.getUserById(id);
	}
	
	@Override
	public User getUser(String name) throws DataAccessException {
		return mapper.getUserByName(name);
	}

	@Override
	public List<User> getAllUser(Integer start, Integer num) throws DataAccessException {
		return mapper.getAllUser(start, num);
	}
	
	@Override
	public int getCount(String param) {
		return mapper.getCount(param);
	}

	@Override
	public void updataUser(User user) throws DataAccessException {
		mapper.updataUser(user);
	}

	@Override
	public void deleteUser(Integer id) throws DataAccessException {
		
		//mapper.deleteUser(id);
	}

	@Override
	public void addUser(User user) throws DataAccessException {
		mapper.addUser(user);
	}
}
