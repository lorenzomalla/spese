package com.accenture.dsg.backend.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Users;
import com.accenture.dsg.backend.repository.UsersDao;
@Repository
public class UsersDaoImpl implements UsersDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(Users u) {
		em.persist(u);
	}

	@Override
	public List<Users> getAllList() {
		List<Users> listaAccount =  em.createNamedQuery("Users.findAll",Users.class).getResultList();
		return listaAccount;
	}
	
	
}
