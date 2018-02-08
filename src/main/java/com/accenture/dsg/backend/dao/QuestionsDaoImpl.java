package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Question;

@Transactional
@Repository
public class QuestionsDaoImpl implements QuestionsDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void persistQuestions(Question q) {
		em.persist(q);
	}
	
	
	
	
}
