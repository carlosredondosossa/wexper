package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Empresario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import dao.IEmpresarioDAO;

public class EmpresarioDAO implements IEmpresarioDAO {

	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<Empresario> persistentClass;
	
	public EmpresarioDAO(){
		this.persistentClass = Empresario.class;
	}
	public Class<Empresario> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<Empresario> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override
	@Transactional
	public Empresario crear(Empresario empresario) {
		em.persist(empresario);
		return empresario;
	}
	
	@Override
	@Transactional
	public Empresario editar(Empresario empresario) {
		em.merge(empresario);
		return empresario;
	}
	
	@Override @Transactional(readOnly = true)
	public Empresario findByUsername(String username) {
		Session sc = (Session) em.getDelegate();
		Criteria criteria = sc.createCriteria(persistentClass)
				.add(Restrictions.eq("username", username));
		Empresario empresario = (Empresario) criteria.uniqueResult();
		
		return empresario;	
	}
}
