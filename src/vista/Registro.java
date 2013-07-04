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
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import util.Validator;

@Controller
public class Registro {

	private boolean visTipo;
	private boolean visForm;
	
	private String tipoUsuario;
	
	private boolean visEstudiante;
	private boolean visAsesor;
	private boolean visEmpresario;
	private boolean visRePwd;
	private boolean visCorreo;
	
	private boolean visMsg;
	private String textMsg;
	
	private String nombre;
	private String apellido;
	private String telefono;
	private String celular;
	private String nivel;
	private String tipo;
	private String empresa;
	private String cargo;
	private String correo;
	private String pwd;
	private String rePwd;
	private String question;
	private String answer;
	
	@RequestMapping(value = "/registro.do", method = RequestMethod.GET)
	public String Servicios(HttpServletRequest request,HttpServletResponse response) {
		return "registro/registro";
	}
	
	@Init
	public void init(){
		limpiarForm();
		showView("SEL_TIPO");
	}
	
	@Command
	@NotifyChange("*")
	public void seleccionarTipo(@BindingParam("tipo") String tipo){
		tipoUsuario = tipo;
		
		showView("VER_FORM");
		
		if (tipo.equals("ES")) {
			visEstudiante = true;
		} else if (tipo.equals("AS")) {
			visAsesor = true;
		} else {
			visEmpresario = true;
		}
	}
	
	@Command
	@NotifyChange("visCorreo")
	public void validarCorreo() {
		visCorreo = (! Validator.validarCorreo(correo)) || ! validarDuplicados() ? false : true;
	}
	
	@Command
	@NotifyChange("visRePwd")
	public void compararPwd() {
		if (pwd != null && rePwd != null)
			visRePwd = (! pwd.equals(rePwd)) ? false : true;
		else
			visRePwd = false;
	}
	
	@Command
	@NotifyChange("*")
	public void retornar() {
		init();
	}
	
	@Command
	@NotifyChange("*")
	public void crear() {
		if(faltanLlenarCampos()){
			verMsg("Debe ingresar todos los campos obligatorios.");
			return;
		}
		
		if(! Validator.validarCorreo(correo)){
			verMsg("El correo ingresado no es válido.");
			return;
		}
		
		if(! validarDuplicados()){
			verMsg("El correo ingresado ya existe.");
			return;
		}
		
		if(validarPwdLong()){
			verMsg("La contraseña debe tener mínimo 8 caracteres.");
			return;
		}
		
		if(validarPwd()){
			verMsg("Las contraseñas ingresadas no son iguales.");
			return;
		}
		
		User user = obtenerDatosUser();
		user = ServiceLocator.getUserDAO().crear(user);
		
		if("ES".equals(tipoUsuario)) {
			Estudiante est = obtenerDatosES(user);
			est = ServiceLocator.getEstudianteDAO().crear(est);
		} else if("AS".equals(tipoUsuario)) {
			Asesor ase = obtenerDatosAS(user);
			ase = ServiceLocator.getAsesorDAO().crear(ase);
		} else {
			Empresario emp = obtenerDatosEM(user);
			emp = ServiceLocator.getEmpresarioDAO().crear(emp);
		}
		showView("");
		verMsg("Usuario creado con éxito. Un correo le informara cuando se encuentre activo.");
	}
	
	private boolean faltanLlenarCampos(){
		boolean faltan = false;

		if (nombre == null || nombre.trim().isEmpty()) {
			faltan = true;
		}
		if (apellido == null || apellido.trim().isEmpty()) {
			faltan = true;
		}
		if (nivel == null && tipoUsuario.equals("ES")) {
			faltan = true;
		}
		if (tipo == null && tipoUsuario.equals("AS")) {
			faltan = true;
		}
		if (empresa == null && tipoUsuario.equals("EM")) {
			faltan = true;
		}
		if (cargo == null && tipoUsuario.equals("EM")) {
			faltan = true;
		}
		if (correo == null || correo.trim().isEmpty()) {
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
	
	private boolean validarDuplicados() {
		boolean unico = false;
		if(ServiceLocator.getUserDAO().getById(correo) == null) { unico = true; }
		return unico;
	}
	
	private boolean validarPwdLong() {
		boolean falta = false;
		if(pwd.length() < 8) { falta = true; }
		return falta;
	}
	
	private boolean validarPwd() {
		boolean unico = false;
		if(! pwd.equals(rePwd)) { unico = true; }
		return unico;
	}
	
	private void verMsg(String msg){
		textMsg = msg;
		visMsg = true;
	}
	
	private User obtenerDatosUser() {
		User user = new User();
		user.setUsername(correo);
		user.setPassword(Validator.generarPwd(pwd));
		user.setEnabled(true);
		user.setEstado("P");
		user.setTipo(tipoUsuario);
		user.setQuestion(question);
		user.setAnswer(Validator.generarPwd(answer));
		return user;
	}
	
	private Estudiante obtenerDatosES(User user) {
		Estudiante est = new Estudiante();
		est.setNombre(nombre);
		est.setApellido(apellido);
		est.setTelefono(telefono);
		est.setCelular(celular);
		est.setNivel(nivel);
		est.setUsername(user);
		return est;
	}
	
	private Asesor obtenerDatosAS(User user) {
		Asesor ase = new Asesor();
		ase.setNombre(nombre);
		ase.setApellido(apellido);
		ase.setTelefono(telefono);
		ase.setCelular(celular);
		ase.setTipo(tipo);
		ase.setUsername(user);
		return ase;
	}
	
	private Empresario obtenerDatosEM(User user) {
		Empresario emp = new Empresario();
		emp.setNombre(nombre);
		emp.setApellido(apellido);
		emp.setTelefono(telefono);
		emp.setCelular(celular);
		emp.setEmpresa(empresa);
		emp.setCargo(cargo);
		emp.setUsername(user);
		return emp;
	}
	
	private void limpiarForm() {
		nombre =  
		apellido = 
		telefono = 
		celular = 
		nivel =
		tipo =
		empresa =
		cargo = 
		correo = 
		pwd =
		rePwd = 
		question =
		answer = null;
	}
	
	private void showView(String accion) {
		
		textMsg = "";
		visMsg = false;
		
		visTipo = visForm = false;
		
		visEstudiante = visAsesor = visEmpresario = visRePwd = visCorreo = false;
	    
	    if("SEL_TIPO".equals(accion)){
	    	visTipo = true;
		}
	    else if("VER_FORM".equals(accion)){
	    	visForm = true;
		}
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

	public boolean isVisEstudiante() {
		return visEstudiante;
	}

	public void setVisEstudiante(boolean visEstudiante) {
		this.visEstudiante = visEstudiante;
	}

	public boolean isVisAsesor() {
		return visAsesor;
	}

	public void setVisAsesor(boolean visAsesor) {
		this.visAsesor = visAsesor;
	}

	public boolean isVisEmpresario() {
		return visEmpresario;
	}

	public void setVisEmpresario(boolean visEmpresario) {
		this.visEmpresario = visEmpresario;
	}

	public boolean isVisRePwd() {
		return visRePwd;
	}

	public void setVisRePwd(boolean visRePwd) {
		this.visRePwd = visRePwd;
	}

	public boolean isVisMsg() {
		return visMsg;
	}

	public void setVisMsg(boolean visMsg) {
		this.visMsg = visMsg;
	}

	public String getTextMsg() {
		return textMsg;
	}

	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean isVisCorreo() {
		return visCorreo;
	}

	public void setVisCorreo(boolean visCorreo) {
		this.visCorreo = visCorreo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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
	
}

