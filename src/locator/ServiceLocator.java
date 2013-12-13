package locator;

import modelo.User;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;

import dao.IAdminDAO;
import dao.IAsesorDAO;
import dao.IAuthorityDAO;
import dao.IEmpresarioDAO;
import dao.IEstudianteDAO;
import dao.IOfertaDAO;
import dao.IUserDAO;

public class ServiceLocator {
	
	
	public ServiceLocator() {}
	
	public static String getBean(String key) {
		return (String) SpringUtil.getBean(key, String.class);
	}
	
	
	public static Object getPerfil(String cod){
		Object perfil = new Object();
		String username;
		username = (String) Sessions.getCurrent().getAttribute("USERNAME");
		User user = ServiceLocator.getUserDAO().getById(username);
		
		switch (cod) {
		case "ES":
			perfil = ServiceLocator.getEstudianteDAO().findByUsername(user);
			break;
		case "AI":
			perfil = ServiceLocator.getAsesorDAO().findByUsername(user);
			break;
		case "AE":
			perfil = ServiceLocator.getAsesorDAO().findByUsername(user);
			break;
		case "EM":
			perfil = ServiceLocator.getEmpresarioDAO().findByUsername(user);
			break;
		case "AD":
			perfil = ServiceLocator.getAdminDAO().findByUsername(user);
			break;
		}
		
		return perfil;
	}
	

	public static IUserDAO getUserDAO() {
		return (IUserDAO) SpringUtil.getBean("userDAO", IUserDAO.class);
	}
	
	public static IAuthorityDAO getAuthorityDAO() {
		return (IAuthorityDAO) SpringUtil.getBean("authorityDAO", IAuthorityDAO.class);
	}
	
	public static IEstudianteDAO getEstudianteDAO() {
		return (IEstudianteDAO) SpringUtil.getBean("estudianteDAO", IEstudianteDAO.class);
	}
	
	public static IAsesorDAO getAsesorDAO() {
		return (IAsesorDAO) SpringUtil.getBean("asesorDAO", IAsesorDAO.class);
	}
	
	public static IEmpresarioDAO getEmpresarioDAO() {
		return (IEmpresarioDAO) SpringUtil.getBean("empresarioDAO", IEmpresarioDAO.class);
	}
	
	public static IAdminDAO getAdminDAO() {
		return (IAdminDAO) SpringUtil.getBean("adminDAO", IAdminDAO.class);
	}
	
	public static IOfertaDAO getOfertaDAO() {
		return (IOfertaDAO) SpringUtil.getBean("ofertaDAO", IOfertaDAO.class);
	}
}
