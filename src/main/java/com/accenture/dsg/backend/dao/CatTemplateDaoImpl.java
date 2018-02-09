package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.CatTemplate;
@Transactional
@Repository
public class CatTemplateDaoImpl implements CatTemplateDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistCatTemplate(CatTemplate a) {
		em.persist(a);
	}

}
