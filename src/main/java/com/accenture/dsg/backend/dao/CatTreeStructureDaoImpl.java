package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.CatTreeStructureType;
@Transactional
@Repository
public class CatTreeStructureDaoImpl implements CatTreeStructureDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistCatTreeStructureType(CatTreeStructureType a) {
		em.persist(a);
	}

}
