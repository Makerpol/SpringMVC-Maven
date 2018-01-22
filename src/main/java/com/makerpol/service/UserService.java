package com.makerpol.service;

import java.util.List;

import com.makerpol.entity.User;

public interface UserService<T> {
	public List<User> getAllUser(Integer start, Integer num);
	public int getCount(String param);
	public List<User> getUserList(String param,Integer start, Integer num);
	public User  getUser(Integer id);
	public User  getUser(String name);
	public void updataUser(User user);
	public void deleteUser(Integer id);
	public void addUser(User user);
}
