package com.makerpol.dao;

import java.util.List;
import com.makerpol.entity.User;

public interface UserMapper {
		public List<User> getAllUser(Integer start, Integer num);
		public User getUserByName(String name);
		public User getUserById(Integer id);
		public List<User> getUserList(String param,Integer start, Integer num);
		public int getCount(String param);
		
		public void updataUser(User user);
		public void deleteUser(Integer id);
		public void addUser(User user);
}