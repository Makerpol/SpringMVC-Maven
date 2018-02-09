package com.makerpol.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.User;

public interface UserService<T> {
	public List<User> getAllUser(Integer start, Integer num) throws DataAccessException ;
	public int getCount(String param) throws DataAccessException ;
	public List<User> getUserList(String param,Integer start, Integer num) throws DataAccessException ;
	public User  getUser(Integer id) throws DataAccessException ;
	public User  getUser(String name) throws DataAccessException ;
	public void updataUser(User user) throws DataAccessException ;
	public void deleteUser(Integer id) throws DataAccessException ;
	public void addUser(User user) throws DataAccessException ;
}
