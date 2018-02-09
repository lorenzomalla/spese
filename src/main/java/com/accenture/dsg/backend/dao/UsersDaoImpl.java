package com.accenture.dsg.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.User;
@Repository
@Transactional
public class UsersDaoImpl implements UsersDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(User u) {
		em.persist(u);
	}

	@Override
	public List<User> getAllList() {
		List<User> listaAccount =  em.createNamedQuery("Users.findAll",User.class).getResultList();
		return listaAccount;
	}

	@Override
	public User checkLogin(String email, String password) {
		User account = null;
		try{
			account = em.createQuery("FROM Users u WHERE u.mail = :mail"
					+ " AND u.password = :password",User.class)
			.setParameter("mail", email).setParameter("password", password).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	
	
}
