package vista;

import locator.ServiceLocator;
import modelo.User;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

@Controller
public class Login {

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String perfil_Inicio() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "redirect:/home.do";
		}
		return "login";
	}
	
	@RequestMapping(value="/loginfailed.do", method = RequestMethod.GET)
	public String loginerror() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "redirect:/home.do";
		}
		return "login";
	}
	
//	Class attributes 
	private enum estadosUsuario {A, I, P};
	
	private final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";
	
	private final String LOGIN_ERROR_USER_NOT_FOUND = "El usuario o contraseña es incorrecta.";
	private final String LOGIN_ERROR_USER_PENDING = "El usuario no ha sido activado.";
	private final String LOGIN_ERROR_USER_DISABLED = "El usuario se encuentra inactivo.";
	
	private String msg = new String();
	
	@Init
	public void init() {
		this.check();
	}
	
	private void check() {
		
		String lastUsername = (String) Sessions.getCurrent().removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (lastUsername == null || lastUsername.isEmpty()) {
//			Missing data input
			setMsg("");
			return;
		}
		
//		Verify user-name
		User user = ServiceLocator.getUserDAO().getById(lastUsername);
		if (user == null || user.getUsername()==null || user.getUsername().isEmpty()){
//			User not fount into system
			setMsg(LOGIN_ERROR_USER_NOT_FOUND);
			return;
		}
		
//		Verify user pending
		if ( user.getEstado().equals(estadosUsuario.P.name()) ) {
			setMsg(LOGIN_ERROR_USER_PENDING);
			return;
		}
		
//		Verify user disabled
		if ( user.getEstado().equals(estadosUsuario.I.name()) ) {
			setMsg(LOGIN_ERROR_USER_DISABLED);
			return;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

