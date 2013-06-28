package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;

@Controller
public class Registro {

	private boolean visTipo;
	private boolean visForm;
	
	@RequestMapping(value = "/registro.do", method = RequestMethod.GET)
	public String Servicios(HttpServletRequest request,HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ) {
			return "registro/registro";
		}
		return "redirect:/login.do";
	}
	
	@Init
	public void init(){
		visForm = true;
	}

	public boolean isVisTipo() {
		return visTipo;
	}

	public void setVisTipo(boolean visTipo) {
		this.visTipo = visTipo;
	}

	public boolean isVisForm() {
		return visForm;
	}

	public void setVisForm(boolean visForm) {
		this.visForm = visForm;
	}
	
}

