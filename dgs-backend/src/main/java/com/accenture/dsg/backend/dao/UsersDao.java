package com.accenture.dsg.backend.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UsersDao {
	
	public void persist(Users a);
	public List<Users> getAllList();
	public Users checkLogin(String email,String password);

	
}
