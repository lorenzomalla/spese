package hello;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
@Repository
public class AccountDaoImpl implements AccountDao{
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public void add(Account a) {
		em.persist(a);
	}
	
	

}
