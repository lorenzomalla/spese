package com.accenture.dsg.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Users;
@Repository
@Transactional
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

	@Override
	public Users checkLogin(String email, String password) {
		Users account = null;
		try{
			account = em.createQuery("FROM Users u WHERE u.mail = :mail"
					+ " AND u.password = :password",Users.class)
			.setParameter("mail", email).setParameter("password", password).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	
	
}
