package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locator.ServiceLocator;
import modelo.Asesor;
import modelo.Empresario;
import modelo.Estudiante;
import modelo.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import util.Validator;

@Controller
public class ForgotPassword {
	
	private boolean visUser;
	private boolean visPwd;
	private boolean visLogin;
	
	private String username;
	private String question;
	private String answer;
	private String pwd;
	private String rePwd;
	
	User user = new User();
	
	public String msg = new String();
	
	@RequestMapping(value = "forgotpwd.do", method = RequestMethod.GET)
	public ModelAndView Servicios(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/forgotPassword");
		return mav;
	}
	
	@Init
	public void init(){
		visUser = true;
		visPwd = false;
		visLogin = false;
		
		username =
		question =
		answer =
		pwd = 
		rePwd = "";
	}
	
	@Command
	@NotifyChange("*")
	public void busacarUsuario(){
		user = ServiceLocator.getUserDAO().getById(username);
		
		if (user == null)
			msg = "El correo ingresado no existe.";
		else {
			question = user.getQuestion();
			visUser = false;
			visPwd = true;
			msg = "";
		}
	}
	
	@Command
	@NotifyChange("*")
	public void cambiarPassword() {
		
		if(faltanLlenarCampos()) {
			msg = "Debe ingresar todos los campos.";
			return;
		}
		
		if(validarRespuesta()) {
			msg = "La respuesta no coincide.";
			return;
		}
	
		if(validarPwdLong()) {
			msg = "Debe tener mínimo 8 caracteres.";
			return;
		}
		
		if(compararPwd()) {
			msg = "Las contraseñas no son iguales.";
			return;
		}
		
		user.setPassword(Validator.generarPwd(pwd));
		user = ServiceLocator.getUserDAO().editar(user);
		msg = "Contraseña cambiada exitosamente.";
		visPwd = false;
		visLogin = true;
	}
	
	private boolean compararPwd() {
		if (pwd != null && rePwd != null)
			return (! pwd.equals(rePwd));
		else
			return false;
	}
	
	private boolean validarPwdLong() {
			return (pwd != null && pwd.length() < 8);
	}
	
	private boolean validarRespuesta() {
		return (answer != null && !user.getAnswer().equals(Validator.generarPwd(answer)));
	}
	
	private boolean faltanLlenarCampos(){
		boolean faltan = false;

		if (answer == null || answer.trim().isEmpty()) {
			faltan = true;
		}
		if (pwd == null || pwd.trim().isEmpty()) {
			faltan = true;
		}
		if (rePwd == null || rePwd.trim().isEmpty()) {
			faltan = true;
		}
		return faltan;
	}
	

	public boolean isVisUser() {
		return visUser;
	}

	public void setVisUser(boolean visUser) {
		this.visUser = visUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isVisLogin() {
		return visLogin;
	}

	public void setVisLogin(boolean visLogin) {
		this.visLogin = visLogin;
	}

	public boolean isVisPwd() {
		return visPwd;
	}

	public void setVisPwd(boolean visPwd) {
		this.visPwd = visPwd;
	}

}
