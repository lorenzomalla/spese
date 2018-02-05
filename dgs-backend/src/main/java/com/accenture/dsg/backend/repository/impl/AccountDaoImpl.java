package com.accenture.dsg.backend.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Account;
import com.accenture.dsg.backend.repository.AccountDao;
@Repository
public class AccountDaoImpl implements AccountDao{
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public void add(Account a) {
		em.persist(a);
	}
	
	

}
