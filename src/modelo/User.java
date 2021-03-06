package modelo;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 7167592027105665275L;
	
	@Id
	@Column(name = "username", length=20,  nullable=false, unique=true)
	private String username;
	
	@Column(name = "password", length=125, nullable=false)
	private String password;
	
	@Column(name = "enabled", nullable=false)
	private boolean enabled;
	
	@Column(name = "estado",length=1, nullable=false)
	private String estado;
	
	@Column(name = "tipo",length=2, nullable=false)
	private String tipo;
	
	@Column(name = "correo", length=60, nullable=false, unique=true)
	private String correo;
	
	@Column(name = "security_question", length=600)
	private String question;

	@Column(name = "security_answer", length=600)
	private String answer;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
