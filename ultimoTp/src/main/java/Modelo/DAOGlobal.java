package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entity.Entidad;
import Entity.Indicador;
import Entity.Metodologia;

public interface DAOGlobal {
	
	//indicador
	
	public void add(Entidad entidad) throws IOException;
	public void delete(String nombre);
	public void update(Indicador indicador,String nombre);
	public ArrayList<Indicador> getAll();
	public List<Indicador> find(String nombre);
	public void addAllStruct() throws IOException;
	
	//metodologia
	//
}
