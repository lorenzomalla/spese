package com.accenture.dsg.backend.repository;

import java.util.List;

import com.accenture.dsg.backend.configuration.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersDao {
	
	public void persist(Users a);
	public List<Users> getAllList();

	
}
