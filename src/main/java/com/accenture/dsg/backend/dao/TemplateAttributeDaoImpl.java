package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.TemplateAttribute;
@Transactional
@Repository
public class TemplateAttributeDaoImpl implements TemplateAttributeDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistTemplateAttribute(TemplateAttribute a) {
		em.persist(a);
	}

}
