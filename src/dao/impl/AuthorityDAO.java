package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import modelo.Authority;
import dao.IAuthorityDAO;

public class AuthorityDAO implements IAuthorityDAO{

	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<Authority> persistentClass;
	
	public AuthorityDAO(){
		this.persistentClass = Authority.class;
	}
	public Class<Authority> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<Authority> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override @Transactional
	public Authority crear(Authority authority) {
		try {
			em.persist(authority);
			return authority;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
