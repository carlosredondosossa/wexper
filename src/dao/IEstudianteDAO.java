package dao;

import modelo.Estudiante;

public interface IEstudianteDAO {

	public Estudiante crear(Estudiante estudiante);
	public Estudiante editar(Estudiante estudiante);
	public Estudiante findByUsername(String username);
}
