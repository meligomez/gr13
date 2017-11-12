package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import Modelo.DAOmetodologiaJson;

@Entity
@Table(name = "Metodologia")
public class Metodologia implements Entidad{
	
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	
	@OneToMany(mappedBy = "metodologia")
	private List<CondicionTaxativa> condiciones;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
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
		public List<CondicionTaxativa> getCondiciones()  {
			return condiciones;
		}
		public void setCondiciones(List<CondicionTaxativa> condiciones) {
			this.condiciones = condiciones;
		}
		public boolean cumpleCondiciones(String empresa,String desde, String hasta,Metodologia metodologia) {
//			DAOmetodologiaJson dao= new DAOmetodologiaJson();
//			ArrayList<Metodologia> metodologias=dao.getAll();
//			ArrayList<CondicionTaxativa> condiciones= metodologias.get(0).getCondiciones();
			//ArrayList<CondicionTaxativa> condiciones= this.condicionesQueCumplen(desde,hasta,empresa);
			List<CondicionTaxativa> condiciones= metodologia.getCondiciones();
			return condiciones.stream().allMatch(c -> c.cumpleCondicion(empresa, desde, hasta));
			//return condiciones.get(0).cumpleCondicion(empresa,desde,hasta);
		}
		public List<CondicionTaxativa> condicionesQueCumplen(String desde, String hasta,String empresa)
		{
			DAOmetodologiaJson dao= new DAOmetodologiaJson();
			ArrayList<Metodologia> metodologias=dao.getAll();
			Cuenta cuenta= new Cuenta();
			List<CondicionTaxativa> condiciones=metodologias.get(0).getCondiciones();
			List<CondicionTaxativa> condiciones2 = new ArrayList<CondicionTaxativa>();
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
