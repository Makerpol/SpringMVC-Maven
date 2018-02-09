package com.makerpol.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.User;

public interface UserMapper {
		public List<User> getAllUser(Integer start, Integer num) throws DataAccessException;
		public User getUserByName(String name) throws DataAccessException;
		public User getUserById(Integer id) throws DataAccessException;
		public List<User> getUserList(String param,Integer start, Integer num) throws DataAccessException;
		public int getCount(String param) throws DataAccessException;
		
		public void updataUser(User user) throws DataAccessException;
		public void deleteUser(Integer id) throws DataAccessException;
		public void addUser(User user) throws DataAccessException;
}