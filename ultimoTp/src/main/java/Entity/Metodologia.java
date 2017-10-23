package Entity;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Modelo.DAOmetodologiaJson;

public class Metodologia {
	String nombre;
	ArrayList<CondicionTaxativa> condiciones;
	
	//constructor agregue para alta
	 public Metodologia(String nombre, ArrayList<CondicionTaxativa> condiciones) {
		super();
		this.nombre = nombre;
		this.condiciones = condiciones;
	}
	
	 //Agrego por que me tira errores..
	public Metodologia(){
		
	}
	
	public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public ArrayList<CondicionTaxativa> getCondiciones()  {
			return condiciones;
		}
		public void setCondiciones(ArrayList<CondicionTaxativa> condiciones) {
			this.condiciones = condiciones;
		}
		public boolean cumpleCondiciones(String empresa,String desde, String hasta,Metodologia metodologia) {
//			DAOmetodologiaJson dao= new DAOmetodologiaJson();
//			ArrayList<Metodologia> metodologias=dao.getAll();
//			ArrayList<CondicionTaxativa> condiciones= metodologias.get(0).getCondiciones();
			//ArrayList<CondicionTaxativa> condiciones= this.condicionesQueCumplen(desde,hasta,empresa);
			ArrayList<CondicionTaxativa> condiciones= metodologia.getCondiciones();
			return condiciones.stream().allMatch(c -> c.cumpleCondicion(empresa, desde, hasta));
			//return condiciones.get(0).cumpleCondicion(empresa,desde,hasta);
		}
		public ArrayList<CondicionTaxativa> condicionesQueCumplen(String desde, String hasta,String empresa)
		{
			DAOmetodologiaJson dao= new DAOmetodologiaJson();
			ArrayList<Metodologia> metodologias=dao.getAll();
			Cuenta cuenta= new Cuenta();
			ArrayList<CondicionTaxativa> condiciones=metodologias.get(0).getCondiciones();
			ArrayList<CondicionTaxativa> condiciones2 = new ArrayList<CondicionTaxativa>();
			//System.out.println(condiciones.get(1).indicadorOCuenta);
			condiciones2=(ArrayList<CondicionTaxativa>) condiciones.stream().filter(condicion->cuenta.getNombresCuentasPorPeriodo(desde, hasta, empresa).equals(condicion.getIndicadorOCuenta())).collect(Collectors.toList());
		
			return condiciones2;
		}
		
		public boolean perteneceMetodologia(String nombre)
		{
			ArrayList<Metodologia> listaDeMetodologias  = new ArrayList<Metodologia>();
			DAOmetodologiaJson dao=new DAOmetodologiaJson();
			dao.addAllStruct();
			listaDeMetodologias=dao.getAll();
			
			for(int i = 0; i < listaDeMetodologias.size(); i++) {
			  	if(listaDeMetodologias.get(i).getNombre().equals(nombre))
			  	{
			  	return true;
			  	}
			}
			return false;
		}
		public Metodologia buscarMetodologia(String nombre) {
			Metodologia m = new Metodologia();
			ArrayList<Metodologia> listaDeMetodologias  = new ArrayList<Metodologia>();
			DAOmetodologiaJson dao=new DAOmetodologiaJson();
			dao.addAllStruct();
			listaDeMetodologias=dao.getAll();
			for(int i = 0; i < listaDeMetodologias.size(); i++) {
			  	if(listaDeMetodologias.get(i).getNombre().equals(nombre))
			  	{
			  	m= listaDeMetodologias.get(i);
			  	}
			}
			return m;
		}
}
