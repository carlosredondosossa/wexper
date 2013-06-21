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
@Table(name="authorities")
@SequenceGenerator(name = "AUTORITIES_SEQ", sequenceName = "AUTORITIES_SEQ", initialValue = 1, allocationSize = 1)
public class Authority implements Serializable{

	private static final long serialVersionUID = 364988434922673165L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = SEQUENCE, generator="AUTORITIES_SEQ")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false)
	private User username;
	
	@Column(name="authority", length=50, nullable= false)
	private String authority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
} //End-Class(Authorities)