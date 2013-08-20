package dao;

import modelo.Estudiante;
import modelo.User;

public interface IEstudianteDAO {

	public Estudiante crear(Estudiante estudiante);
	public Estudiante editar(Estudiante estudiante);
	public Estudiante findByUsername(User username);
}
