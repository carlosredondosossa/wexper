package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Asesor;
import modelo.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import dao.IAsesorDAO;

public class AsesorDAO implements IAsesorDAO {
	
	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<Asesor> persistentClass;
	
	public AsesorDAO(){
		this.persistentClass = Asesor.class;
	}
	public Class<Asesor> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<Asesor> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override
	@Transactional
	public Asesor crear(Asesor asesor) {
		em.persist(asesor);
		return asesor;
	}
	
	@Override
	@Transactional
	public Asesor editar(Asesor asesor) {
		em.merge(asesor);
		return asesor;
	}
	
	@Override @Transactional(readOnly = true)
	public Asesor findByUsername(User username) {
		Session sc = (Session) em.getDelegate();
		Criteria criteria = sc.createCriteria(persistentClass)
				.add(Restrictions.eq("username", username));
		Asesor asesor = (Asesor) criteria.uniqueResult();
		
		return asesor;	
	}

}
