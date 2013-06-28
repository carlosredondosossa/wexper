package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 7167592027105665275L;
	
	@Id
	@Column(name = "username", length=60,  nullable=false, unique=true)
	private String username;
	
	@Column(name = "password", length=125, nullable=false)
	private String password;
	
	@Column(name = "enabled", nullable=false)
	private boolean enabled;
	
	@Column(name = "estado",length=1, nullable=false)
	private String estado;
	
	@Column(name = "tipo",length=2, nullable=false)
	private String tipo;
	
	@Column(name = "security_question", length=600)
	private String securityQuestion;

	@Column(name = "security_answer", length=600)
	private String securityAnswer;

	
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

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
}
