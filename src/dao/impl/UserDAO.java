package dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import modelo.User;
import dao.IUserDAO;

public class UserDAO implements IUserDAO{

	@PersistenceContext(name="wexper")
	private EntityManager em;
	
	private Class<User> persistentClass;
	
	public UserDAO(){
		this.persistentClass = User.class;
	}
	public Class<User> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<User> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	@Override @Transactional(readOnly=true)
	public Collection<User> listarAll() {
		try {
			Criteria crt = ((Session) em.getDelegate()).createCriteria(persistentClass);
			crt.addOrder(Order.asc("username"));
			Collection<User> lista = (Collection<User>) crt.list();
			return lista;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean validarEdicion(User user) {
		return false;
	} //End-Function(validarEdicion)

	@Override @Transactional
	public User editar(User user) {
		try {
			if ( user.getUsername() != null && !user.getUsername().isEmpty() ) {
				em.merge(user);
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override @Transactional
	public User crear(User user) {
		try {
			em.persist(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override @Transactional
	public boolean eliminar(User user) {
		try {
			if ( user.getUsername() != null && !user.getUsername().isEmpty() ) {
				User u = em.find(persistentClass, user.getUsername());
				em.remove(u);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override @Transactional(readOnly=true)
	public User getById(String username) {
		try {
			Criteria crt = ((Session) em.getDelegate()).createCriteria(persistentClass);
			crt.add(Restrictions.eq("username", username));
			return (User) crt.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override @Transactional(readOnly=true)
	public User getByCorreo(String correo) {
		try {
			Criteria crt = ((Session) em.getDelegate()).createCriteria(persistentClass);
			crt.add(Restrictions.eq("correo", correo));
			return (User) crt.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
