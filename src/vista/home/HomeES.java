package vista.home;

import locator.ServiceLocator;
import modelo.Estudiante;
import modelo.User;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;

import util.Validator;

@Controller
public class HomeES {
	
	private String msg = new String();
	
	private String nivel;
	private String correo;
	private String telefono;
	private String celular;
	private String pwd;
	private String newPwd;
	private String rePwd;
	
	private boolean visPwd;
	private boolean visCorreo;
	
	private User usuario = new User();
	private Estudiante est = new Estudiante();

	@RequestMapping(value = "/homeES.do", method = RequestMethod.GET)
	public String requestMappingServicios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() )
			return "home/homeES";
		else
			return "redirect:/login.do";
	}
	
	@Init
	public void init(){
		
		String username = (String) Sessions.getCurrent().getAttribute("USERNAME").toString();
		usuario = ServiceLocator.getUserDAO().getById(username);
		
		est = ServiceLocator.getEstudianteDAO().findByUsername(usuario);
		
		correo = usuario.getCorreo();
		telefono = est.getTelefono();
		celular = est.getCelular();
		
		nivel = est.getNivel();
		
		pwd = new String();
		newPwd = new String();
		rePwd = new String();
		
		visCorreo = false;
		visPwd = false;
		
	}
	
	@Command
	@NotifyChange("*")
	public void modify() {
		if(faltanLlenarCorreo()){
			msg = "Debe ingresar una dirección de correo.";
			return;
		}
		
		if(! Validator.validarCorreo(correo.toLowerCase())){
			msg = "El correo ingresado no es válido.";
			return;
		}
		
		if(! validarDuplicadosCorreo()){
			msg = "El correo ingresado ya existe.";
			return;
		}
		
		usuario.setCorreo(correo.toLowerCase());
		est.setTelefono(telefono);
		est.setCelular(celular);
		
		usuario = ServiceLocator.getUserDAO().editar(usuario);
		est = ServiceLocator.getEstudianteDAO().editar(est);
		
		init();

		msg = "Datos personales modificados correctamente.";
	}
	
	@Command
	@NotifyChange("*")
	public void changePwd() {
		
		if(faltanLlenarPwd()){
			msg = "Debe ingresar todos los campos de contraseña.";
			return;
		}
		
		if(validarPwdActual()){
			msg = "La contraseña actual es incorrecta.";
			return;
		}
		
		if(validarPwdLong()){
			msg = "La contraseña debe tener mínimo 8 caracteres.";
			return;
		}
		
		if(validarPwd()){
			msg = "Las contraseñas ingresadas no son iguales.";
			return;
		}
		
		usuario.setPassword(Validator.generarPwd(newPwd));
		
		usuario = ServiceLocator.getUserDAO().editar(usuario);
		
		init();

		msg = "Se ha cambiado su contraseña correctamente.";
	}
	
	@Command
	@NotifyChange("visCorreo")
	public void validarCorreo() {
		visCorreo = (! Validator.validarCorreo(correo.toLowerCase())) || ! validarDuplicadosCorreo() ? false : true;
	}
	
	@Command
	@NotifyChange("visPwd")
	public void compararPwd() {
		visPwd = false;
		if (validarPwdLong())
			return;
		if (pwd != null && rePwd != null)
			visPwd = (! newPwd.equals(rePwd)) ? false : true;
	}
	
	private boolean validarPwdActual() {
		boolean unico = false;
		
		if(! Validator.generarPwd(pwd).equals(usuario.getPassword())) { unico = true; }
		return unico;
	}
	
	private boolean validarPwdLong() {
		boolean falta = false;
		if(newPwd != null && newPwd.length() < 8) { falta = true; }
		return falta;
	}
	
	private boolean validarPwd() {
		boolean unico = false;
		if(! newPwd.equals(rePwd)) { unico = true; }
		return unico;
	}
	
	private boolean validarDuplicadosCorreo() {
		boolean unico = false;
		User user = ServiceLocator.getUserDAO().getByCorreo(correo.toLowerCase());
		if( user == null || user.getUsername().equals(usuario.getUsername())) { unico = true; }
		return unico;
	}
	
	private boolean faltanLlenarCorreo(){
		boolean faltan = false;

		if (correo == null || correo.trim().isEmpty()) {
			faltan = true;
		}
		return faltan;
	}
	
	private boolean faltanLlenarPwd(){
		boolean faltan = false;

		if (pwd == null || pwd.trim().isEmpty()) {
			faltan = true;
		}
		if (newPwd == null || newPwd.trim().isEmpty()) {
			faltan = true;
		}
		if (rePwd == null || rePwd.trim().isEmpty()) {
			faltan = true;
		}
		return faltan;
	}
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public boolean isVisPwd() {
		return visPwd;
	}

	public void setVisPwd(boolean visPwd) {
		this.visPwd = visPwd;
	}

	public boolean isVisCorreo() {
		return visCorreo;
	}

	public void setVisCorreo(boolean visCorreo) {
		this.visCorreo = visCorreo;
	}

}