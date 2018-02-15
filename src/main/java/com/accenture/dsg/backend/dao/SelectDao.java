package com.accenture.dsg.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Select;

@Repository
public interface SelectDao {
	
	public void persist(Select a);
	public List<Select> getAllList();
	
}
