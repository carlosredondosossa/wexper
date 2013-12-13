package modelo;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OFERTA")
@SequenceGenerator(name = "OFERTA_SEQ", sequenceName = "OFERTA_SEQ", initialValue = 1, allocationSize = 1)
public class Oferta implements Serializable {
	
	private static final long serialVersionUID = 8087224495827756769L;

	@Id
	@Column(name = "id", nullable=false)
	@GeneratedValue(strategy = SEQUENCE, generator="OFERTA_SEQ")
	private Integer id;
	
	@Column(name = "fecha", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "empresa")
	private String empresa;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "funciones")
	private String funciones;
	
	@Column(name = "salario")
	private Integer salario;
	
	@Column(name = "contacto")
	private String contacto;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "estado")
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name="empleador")
	private Empresario empleador;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public Integer getSalario() {
		return salario;
	}

	public void setSalario(Integer salario) {
		this.salario = salario;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Empresario getEmpleador() {
		return empleador;
	}

	public void setEmpleador(Empresario empleador) {
		this.empleador = empleador;
	}
	
}
