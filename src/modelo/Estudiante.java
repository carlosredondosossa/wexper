package modelo;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ESTUDIANTE")
@SequenceGenerator(name = "ESTUDIANTE_SEQ", sequenceName = "ESTUDIANTE_SEQ", initialValue = 1, allocationSize = 1)
public class Estudiante implements Serializable {

	private static final long serialVersionUID = 4422361742585748214L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = SEQUENCE, generator="ESTUDIANTE_SEQ")
	private Integer id;
	
	@Column(name = "NOMBRE", nullable=false, length=60) 
	private String nombre;
	
	@Column(name = "APELLIDO", nullable=false, length=60) 
	private String apellido;
	
	@Column(name = "TELEFONO", length=10) 
	private String telefono;
	
	@Column(name = "CELULAR", length=12) 
	private String celular;
	
	@Column(name = "CORREO", length=60) 
	private String correo;
	
	@Column(name = "NIVEL", nullable=false, length=12) 
	private String nivel;
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false)
	private User username;
	
	
	public String getName(){
		return nombre + " " + apellido;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getNivel() {
		return nivel;
	}


	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public User getUsername() {
		return username;
	}


	public void setUsername(User username) {
		this.username = username;
	}
}
