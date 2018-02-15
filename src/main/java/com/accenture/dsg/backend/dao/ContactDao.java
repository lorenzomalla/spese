package com.accenture.dsg.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Contact;

@Repository
public interface ContactDao {
	
	public void persist(Contact a);
	public List<Contact> getAllList(String option);
	
}
