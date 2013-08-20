package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Admin;
import modelo.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import dao.IAdminDAO;

public class AdminDAO implements IAdminDAO{

	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<Admin> persistentClass;
	
	public AdminDAO(){
		this.persistentClass = Admin.class;
	}
	public Class<Admin> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<Admin> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override
	@Transactional
	public Admin crear(Admin admin) {
		em.persist(admin);
		return admin;
	}
	
	@Override
	@Transactional
	public Admin editar(Admin admin) {
		em.merge(admin);
		return admin;
	}
	
	@Override @Transactional(readOnly = true)
	public Admin findByUsername(User username) {
		Session sc = (Session) em.getDelegate();
		Criteria criteria = sc.createCriteria(persistentClass)
				.add(Restrictions.eq("username", username));
		Admin admin = (Admin) criteria.uniqueResult();
		
		return admin;	
	}
}
