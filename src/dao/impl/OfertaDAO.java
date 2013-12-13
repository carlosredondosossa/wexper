package dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Empresario;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import dao.IEstudianteDAO;
import dao.IOfertaDAO;

public class OfertaDAO implements IOfertaDAO{
	
	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<Oferta> persistentClass;
	
	public OfertaDAO(){
		this.persistentClass = Oferta.class;
	}
	public Class<Oferta> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<Oferta> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override
	@Transactional
	public Oferta crear(Oferta oferta) {
		em.persist(oferta);
		return oferta;
	}
	
	@Override
	@Transactional
	public Oferta editar(Oferta oferta) {
		em.merge(oferta);
		return oferta;
	}
	
	@Override @Transactional(readOnly = true)
	public ArrayList<Oferta> buscarByEmpresario(Empresario empresario) {
		try {
			Criteria crt = ((Session) em.getDelegate()).createCriteria(Oferta.class);
			crt.add(Restrictions.eq("empleador", empresario));
			crt.addOrder(org.hibernate.criterion.Order.desc("estado"));
			crt.addOrder(org.hibernate.criterion.Order.desc("fecha"));
			ArrayList<Oferta> lista = (ArrayList<Oferta>) crt.list();
			return lista;
		} catch (Throwable t) {
			t.printStackTrace();
			return new ArrayList<Oferta>();
		}
	}
	
	@Override @Transactional(readOnly = true)
	public ArrayList<Oferta> buscarAll() {
		try {
			Criteria crt = ((Session) em.getDelegate()).createCriteria(Oferta.class);
			crt.addOrder(org.hibernate.criterion.Order.desc("estado"));
			crt.addOrder(org.hibernate.criterion.Order.desc("fecha"));
			ArrayList<Oferta> lista = (ArrayList<Oferta>) crt.list();
			return lista;
		} catch (Throwable t) {
			t.printStackTrace();
			return new ArrayList<Oferta>();
		}
	}
}
