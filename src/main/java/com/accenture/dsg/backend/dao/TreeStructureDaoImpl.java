package com.accenture.dsg.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.TreeStructure;
import com.accenture.dsg.backend.utils.ResponseObjectWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
@Transactional
@Repository
public class TreeStructureDaoImpl implements TreeStructureDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TreeStructure> getAllLista() {
		List<TreeStructure> listaTree = null;
		try{
			listaTree = em.createNamedQuery("TreeStructure.findAll",TreeStructure.class)
					.getResultList();
		}catch(Exception e){
			System.out.println("Errore");
		}
		return listaTree;
	}

	@Override
	public ResponseObjectWrapper getFindById(int id) {
		ResponseObjectWrapper tree = null;
		try{
			Object tmpObj = new Object();
			tmpObj = em.createQuery("SELECT t FROM TreeStructure t LEFT OUTER JOIN fetch t.questions q "
					+ "LEFT OUTER JOIN fetch t.answers a LEFT OUTER JOIN fetch a.template templ LEFT OUTER JOIN fetch templ.catTemplate ct"
					+ " WHERE t.id = '"+id+"'").getSingleResult();
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(tmpObj));
			tree = (ResponseObjectWrapper) tmpObj;
			//AND a.treeStructure.id = t.id AND a.template.id = templ.id AND q.treeStructure.id = t.id
		}catch(Exception e){
			System.err.println("Errore");
			e.printStackTrace();
		}
		return tree;
	}

	@Override
	public TreeStructure getTree() {
		TreeStructure tree = null;
		try{
			tree = (TreeStructure) em.createQuery("SELECT t FROM TreeStructure t LEFT OUTER JOIN t.answers a LEFT OUTER JOIN a.template templ LEFT OUTER JOIN t.questions q "
					+ " WHERE t.treeStructure.id IS NULL").getSingleResult();
			// AND a.treeStructure.id = t.id AND a.template.id = templ.id AND q.treeStructure.id = t.id
		}catch(Exception e){
			System.err.println("Errore");
			e.printStackTrace();
		}
		return tree;
	}

	@Override
	public void persistTreeStructure(TreeStructure tree) {
		em.persist(tree);
	}

	
	

}
