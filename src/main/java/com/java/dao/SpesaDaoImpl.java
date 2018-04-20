package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.java.model.Spesa;

@Repository
@Transactional
public class SpesaDaoImpl implements SpesaDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistSpesa(Spesa s) {
		em.persist(s);
	}

	@Override
	public List<Spesa> getAllSpesa() {
		List<Spesa> lista = em.createQuery("SELECT s FROM Spesa s",Spesa.class).getResultList();
		return lista;
	}

}
