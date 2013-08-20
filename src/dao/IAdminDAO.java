package dao;

import modelo.Admin;
import modelo.User;

public interface IAdminDAO {

	public Admin crear(Admin admin);
	public Admin editar(Admin admin);
	public Admin findByUsername(User username);
}
