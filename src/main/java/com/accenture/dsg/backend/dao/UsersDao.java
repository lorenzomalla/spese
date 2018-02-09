package com.accenture.dsg.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.User;

@Repository
public interface UsersDao {
	
	public void persist(User a);
	public List<User> getAllList();
	public User checkLogin(String email,String password);

	
}
