package vista.home;

import locator.ServiceLocator;
import modelo.Empresario;
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
public class HomeEM {
	
	private String msg = new String();
	
	private String empresa;
	private String cargo;
	private String correo;
	private String telefono;
	private String celular;
	private String pwd;
	private String newPwd;
	private String rePwd;
	
	private boolean visPwd;
	private boolean visCorreo;
	
	private User usuario = new User();
	private Empresario emp = new Empresario();

	@RequestMapping(value = "/homeEM.do", method = RequestMethod.GET)
	public String requestMappingServicios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() )
			return "home/homeEM";
		else
			return "redirect:/login.do";
	}
	
	@Init
	public void init(){
		
		String username = (String) Sessions.getCurrent().getAttribute("USERNAME").toString();
		usuario = ServiceLocator.getUserDAO().getById(username);
		
		emp = ServiceLocator.getEmpresarioDAO().findByUsername(usuario);
		
		correo = usuario.getCorreo();
		telefono = emp.getTelefono();
		celular = emp.getCelular();
		
		empresa = emp.getEmpresa();
		cargo = emp.getCargo();
		
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
		
		if(faltanLlenarCargo()){
			msg = "Debe ingresar un cargo laboral.";
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
		emp.setTelefono(telefono);
		emp.setCelular(celular);
		emp.setCargo(cargo);
		
		usuario = ServiceLocator.getUserDAO().editar(usuario);
		emp = ServiceLocator.getEmpresarioDAO().editar(emp);
		
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
	
	private boolean faltanLlenarCargo(){
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}