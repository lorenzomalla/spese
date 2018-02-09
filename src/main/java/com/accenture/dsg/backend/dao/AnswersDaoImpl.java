package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Answer;
@Transactional
@Repository
public class AnswersDaoImpl implements AnswersDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistAnswers(Answer a) {
		em.persist(a);
	}

}
