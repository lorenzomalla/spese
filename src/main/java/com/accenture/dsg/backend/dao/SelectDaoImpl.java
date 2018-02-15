package com.accenture.dsg.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Select;
@Repository
@Transactional
public class SelectDaoImpl implements SelectDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(Select s) {
		em.persist(s);
	}

	@Override
	public List<Select> getAllList() {
		List<Select> listaSelect =  em.createNamedQuery("Select.findAll",Select.class).getResultList();
		return listaSelect;
	}

}
