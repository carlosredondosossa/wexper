package vista;

import locator.ServiceLocator;
import modelo.User;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

public class SettingSession {
	
	@RequestMapping(value = "/settingSession.do", method = RequestMethod.GET)
	public String perfil_Inicio() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "settingSession";
		}
		return "redirect:/login.do";
	}
	
	private enum tiposUsuario {ES, AI, AE, EM, AD};
	
	@Init
	public void init() {
		this.load();
	}
	
	private void load() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if ( authentication == null || (authentication instanceof AnonymousAuthenticationToken) || !authentication.isAuthenticated() ) {
			Executions.getCurrent().sendRedirect("/j_spring_security_logout");
			return;
		}
		
		String currentUsername = authentication.getName();
		
		org.zkoss.zk.ui.Session sesion = Sessions.getCurrent();
		User user = ServiceLocator.getUserDAO().getById(currentUsername);
		
		if (user==null || user.getUsername()==null || user.getUsername().isEmpty()) {
			Executions.getCurrent().sendRedirect("/j_spring_security_logout");
			return;
		}
		
		sesion.setAttribute("USERNAME", currentUsername);
		
//		Check the user name to display.
		String nameDisplay;
		
		if (user.getTipo().equals(tiposUsuario.AD.name())) {
			nameDisplay = ServiceLocator.getAdminDAO().findByUsername(currentUsername).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
		} else if (user.getTipo().equals(tiposUsuario.EM.name())) {
			nameDisplay = ServiceLocator.getEmpresarioDAO().findByUsername(currentUsername).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
		} else if (user.getTipo().equals(tiposUsuario.ES.name())) {
			nameDisplay = ServiceLocator.getEstudianteDAO().findByUsername(currentUsername).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
		} else {
			nameDisplay = ServiceLocator.getAsesorDAO().findByUsername(currentUsername).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
			if (user.getTipo().equals(tiposUsuario.AI.name())) {
				
			} else {
					
			}
		}
		
		Executions.getCurrent().sendRedirect("/home.do");
		
	}

}
