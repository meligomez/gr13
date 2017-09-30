package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.Metodologia;

public class RepositorioDeMetodologia {
	private DAOmetodologia daometodologia;

	public RepositorioDeMetodologia(DAOmetodologia dao){
		this.daometodologia = dao;
		
	}
	public RepositorioDeMetodologia(){
		this.daometodologia = new DAOmetodologiaJson();
		
	}

	public void add(Metodologia metodologia) throws IOException{
		this.daometodologia.add(metodologia);
	}

	public void delete(String nombre){
		this.daometodologia.delete(nombre);
	}

	public ArrayList<Metodologia> getAllmetodologias() throws IOException{
		return this.daometodologia.getAll();
	}

	
	public void update(Metodologia metodologia,String nombre){
		this.daometodologia.update(metodologia,nombre);
	}
	
	public void addAllStruct() throws IOException
	{
		this.daometodologia.addAllStruct();
	}
	public List<Metodologia> find(String desde){
		return this.daometodologia.find(desde);
	}
	
	public void writeArray(ArrayList<Metodologia> lista) throws IOException{
		this.daometodologia.writeArray(lista);
	}
}                    