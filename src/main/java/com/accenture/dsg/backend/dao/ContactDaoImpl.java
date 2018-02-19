package com.accenture.dsg.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Contact;
@Repository
@Transactional
public class ContactDaoImpl implements ContactDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(Contact c) {
		em.persist(c);
	}

	@Override
	public List<Contact> getAllList() {
		List<Contact> listaContact =  em.createNamedQuery("Contact.findAll",Contact.class).getResultList();
		return listaContact;
	}
	
	@Override
	public Contact getByRef(int id) {
		Contact singleContact =  em.createNamedQuery("Contact.getByRef",Contact.class)
				.setParameter("id", id)
				.getSingleResult();
		System.out.println(singleContact.toString());
		return singleContact;
	}

	@Override
	public List<Contact> getByBranch(String branch) {
		List<Contact> contactList =  em.createNamedQuery("Contact.getByBranch",Contact.class)
				.setParameter("branch", branch)
				.getResultList();
		System.out.println("-------------------------------> Option count: "+contactList.size());
		return contactList;
	}

}
