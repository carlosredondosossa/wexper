package dao;

import java.util.ArrayList;

import modelo.Empresario;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.User;

public interface IOfertaDAO {
	
	public Oferta crear(Oferta oferta);
	public Oferta editar(Oferta oferta);
	public ArrayList<Oferta> buscarByEmpresario(Empresario empresario);
	public ArrayList<Oferta> buscarAll();

}
