package com.accenture.dsg.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Template;
@Transactional
@Repository
public class TemplateDaoImpl implements TemplateDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persistTemplate(Template a) {
		em.persist(a);
	}

//	@Override
//	public Template getTemplate(int path, int servizio, int canale, int tipoAssistenza) {
//		Template template = null;
//		try{
//			template = (Template) em.createQuery("SELECT t.id, c.id, c.channel, c.branch "
//					+ " FROM TreeStructure t, Contatti c WHERE t.id = '"+path+"' AND c.id = '"+servizio+"'"
//					+ " AND c.channel = '"+canale+"' AND c.branch = '"+tipoAssistenza+"'",Template.class).getSingleResult();
//		}catch(NoResultException e){
//			System.out.println("Errore in getTemplate");
////			e.printStackTrace();
//		}
//		return template;
//	}

}
