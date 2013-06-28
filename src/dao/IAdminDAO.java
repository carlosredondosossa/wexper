package dao;

import modelo.Admin;

public interface IAdminDAO {

	public Admin crear(Admin admin);
	public Admin editar(Admin admin);
	public Admin findByUsername(String username);
}
