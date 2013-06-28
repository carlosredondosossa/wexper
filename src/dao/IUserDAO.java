package dao;

import java.util.Collection;

import modelo.User;

public interface IUserDAO {
	public Collection<User> listarAll();
	public boolean validarEdicion(User user);
	public boolean editar(User user);
	public boolean eliminar(User user);
	public User crear(User user);
	public User getById(String username);
}
