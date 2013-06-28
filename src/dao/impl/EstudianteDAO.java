package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import modelo.Estudiante;
import dao.IEstudianteDAO;

public class EstudianteDAO implements IEstudianteDAO{
	
	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<Estudiante> persistentClass;
	
	public EstudianteDAO(){
		this.persistentClass = Estudiante.class;
	}
	public Class<Estudiante> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<Estudiante> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override
	@Transactional
	public Estudiante crear(Estudiante estudiante) {
		em.persist(estudiante);
		return estudiante;
	}
	
	@Override
	@Transactional
	public Estudiante editar(Estudiante estudiante) {
		em.merge(estudiante);
		return estudiante;
	}
	
	@Override @Transactional(readOnly = true)
	public Estudiante findByUsername(String username) {
		Session sc = (Session) em.getDelegate();
		Criteria criteria = sc.createCriteria(persistentClass)
				.add(Restrictions.eq("username", username));
		Estudiante estudiante = (Estudiante) criteria.uniqueResult();
		
		return estudiante;	
	}
}
