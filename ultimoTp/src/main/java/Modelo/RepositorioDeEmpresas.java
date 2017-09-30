package Modelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.Cuenta;
import Controller.Empresa;
import Modelo.DAOEmpresa;

public class RepositorioDeEmpresas {

	private DAOEmpresa daoEmpresa;

	public RepositorioDeEmpresas(DAOEmpresa dao){
		this.daoEmpresa = dao;
		
	}

	public void add(Empresa empresa) throws IOException{
		this.daoEmpresa.add(empresa);
	}

	public void delete(Empresa empresa){
		this.daoEmpresa.delete(empresa);
	}

	public ArrayList<Empresa> getAllEmpresas() throws IOException{
		return this.daoEmpresa.getAll();
	}

	
	public void update(Empresa empresa,String nombre){
		this.daoEmpresa.update(empresa,nombre);
	}
	
	public void addAllStruct() throws IOException
	{
		this.daoEmpresa.addAllStruct();
	}
	public List<Empresa> find(String desde,String hasta){
		return this.daoEmpresa.find(desde,hasta);
	}
//	public ArrayList<Cuenta> findCta(ArrayList<Empresa> listaDeEmpresas,int anioEnInt){
//		return this.daoEmpresa.findCta(listaDeEmpresas, anioEnInt);
//	}
	public ArrayList<Cuenta> findCtaPorEmpresa(ArrayList<Empresa> listaDeEmpresas,String fecha, String empresa)
	{
		return this.daoEmpresa.findCtaPorEmpresa(listaDeEmpresas, fecha, empresa);
	}

}