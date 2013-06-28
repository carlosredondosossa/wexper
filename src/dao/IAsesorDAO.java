package dao;

import modelo.Asesor;

public interface IAsesorDAO {

	public Asesor crear(Asesor asesor);
	public Asesor editar(Asesor asesor);
	public Asesor findByUsername(String username);
}
