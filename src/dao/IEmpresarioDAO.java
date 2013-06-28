package dao;

import modelo.Empresario;

public interface IEmpresarioDAO {

	public Empresario crear(Empresario empresario);
	public Empresario editar(Empresario empresario);
	public Empresario findByUsername(String username);
}
