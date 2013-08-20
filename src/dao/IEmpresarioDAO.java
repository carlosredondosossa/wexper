package dao;

import modelo.Empresario;
import modelo.User;

public interface IEmpresarioDAO {

	public Empresario crear(Empresario empresario);
	public Empresario editar(Empresario empresario);
	public Empresario findByUsername(User username);
}
