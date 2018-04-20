package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.java.model.Auto;

@Repository
@Transactional
public class AutoDaoImpl implements AutoDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistAuto(Auto a) {
		em.persist(a);
	}

}
