package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locator.ServiceLocator;
import modelo.User;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

@Controller
public class SettingSession {
	
	@RequestMapping(value = "/settingSession.do", method = RequestMethod.GET)
	public String Servicios(HttpServletRequest request,HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "settingSession";
		} else {
			return "redirect:/login.do";
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "redirect:/settingSession.do";
		}
		return "redirect:/login.do";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String inicio_default() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "redirect:/settingSession.do";
		}
		return "redirect:/login.do";
	}	

	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logout() {
		return "redirect:/login.do";
	}
	
	private enum estadosUsuario {A, I, P};
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
		
//		Display window with user status
		if(estadosUsuario.P.name().equals(user.getEstado()) || estadosUsuario.I.name().equals(user.getEstado())){
			Executions.getCurrent().sendRedirect("/login.do");
			return;
		}
		
		sesion.setAttribute("USERNAME", currentUsername);
		
//		Check the user name to display.
		String nameDisplay;
		User usuario = ServiceLocator.getUserDAO().getById(currentUsername);
		
		if (user.getTipo().equals(tiposUsuario.AD.name())) {
			nameDisplay = ServiceLocator.getAdminDAO().findByUsername(usuario).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
			Executions.getCurrent().sendRedirect("/homeAD.do");
		} else if (user.getTipo().equals(tiposUsuario.EM.name())) {
			nameDisplay = ServiceLocator.getEmpresarioDAO().findByUsername(usuario).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
			Executions.getCurrent().sendRedirect("/homeEM.do");
		} else if (user.getTipo().equals(tiposUsuario.ES.name())) {
			nameDisplay = ServiceLocator.getEstudianteDAO().findByUsername(usuario).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
			Executions.getCurrent().sendRedirect("/homeES.do");
		} else {
			nameDisplay = ServiceLocator.getAsesorDAO().findByUsername(usuario).getName();
			sesion.setAttribute("FULL_NAME", nameDisplay);
			if (user.getTipo().equals(tiposUsuario.AI.name())) {
				Executions.getCurrent().sendRedirect("/homeAI.do");
			} else {
				Executions.getCurrent().sendRedirect("/homeAE.do");
			}
		}
	}

}
