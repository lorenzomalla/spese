package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Template;

@Transactional
@Repository
public class TemplateDaoImpl implements TemplateDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void persistTemplate(Template a) {
		em.persist(a);
	}

}
