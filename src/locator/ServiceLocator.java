package locator;

import org.zkoss.zkplus.spring.SpringUtil;

import dao.IAdminDAO;
import dao.IAsesorDAO;
import dao.IEmpresarioDAO;
import dao.IEstudianteDAO;
import dao.IUserDAO;

public class ServiceLocator {
	
	
	public ServiceLocator() {}
	
	public static String getBean(String key) {
		return (String) SpringUtil.getBean(key, String.class);
	}

	public static IUserDAO getUserDAO() {
		return (IUserDAO) SpringUtil.getBean("userDAO", IUserDAO.class);
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
}
