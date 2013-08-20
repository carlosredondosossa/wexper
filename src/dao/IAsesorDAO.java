package dao;

import modelo.Asesor;
import modelo.User;

public interface IAsesorDAO {

	public Asesor crear(Asesor asesor);
	public Asesor editar(Asesor asesor);
	public Asesor findByUsername(User username);
}
