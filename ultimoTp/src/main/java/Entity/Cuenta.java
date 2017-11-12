package Entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Modelo.DAOjson;
/*
 * @Author : Grupo 13
 */

@Entity
@Table(name = "Cuenta")
public class Cuenta implements Entidad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cuenta_id")
	private int id;
	String nombre;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Empresa> empresas;
	
	@OneToMany(mappedBy="cuenta",cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	 
	public Cuenta(){
		super();
		this.empresas=new LinkedList<>();
		periodos = new LinkedList<>();
	}	

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void addEmpresa(Empresa empresa){
		empresas.add(empresa);
	}

	public int getId() {
		return id;
	}


	public List<Periodo> getPeriodo() 
	{
		return periodos;
	}
	public void setPeriodos(List<Periodo> periodo) {
		this.periodos=periodo;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void addPeriodo(Periodo periodo){
		periodos.add(periodo);
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Hacer con stream()
	//public ArrayList<Cuenta> findCtaPorEmpresa(ArrayList<Empresa> listaDeEmpresas,String fecha, String empresa)
	public ArrayList<Cuenta> findCtaPorEmpresa(ArrayList<Empresa> listaDeEmpresas,String fechaDesde,String fechaHasta, String empresa)
	{
		  	ArrayList<Cuenta> cuentasPorEmpr= new ArrayList<Cuenta>();
		  	//de todas las empresas con cuentas en ese periodo selecciona la empresa pedida y sus cuentas
		  	for (int i = 0; i < listaDeEmpresas.size(); i++)
		  	{
		  		if(listaDeEmpresas.get(i).getNombre().equals(empresa))
		  		{
		  			List<Cuenta> cuentasPorEmpresa = listaDeEmpresas.get(i).getCuentas();
		  			for(int j=0;j<cuentasPorEmpresa.size();j++)
		  			{
		  					List<Periodo> periodosPorCuenta=cuentasPorEmpresa.get(j).getPeriodo();
		  					for(int k=0;k<periodosPorCuenta.size();k++)
		  					{
		  		  				if(periodosPorCuenta.get(k).getDesde().equals(fechaDesde) && periodosPorCuenta.get(k).getHasta().equals(fechaHasta)) 
		  		  				{
		  		  					cuentasPorEmpr.add(cuentasPorEmpresa.get(j));
		  		  				}
		  					}
		  					
		  				
		  			}
		  		}
		  	}
		  	return cuentasPorEmpr;
	}
	
	public List<Cuenta> getCuentasPorPeriodo(String desde, String hasta, String empresa) 
	{
		DAOjson daojson= new DAOjson();
		//RepositorioDeEmpresas repoEmpresas= new RepositorioDeEmpresas(daojson);
		ArrayList<Empresa> listaDeEmpresas= daojson.getAll();
		ArrayList<Cuenta> cuentas = this.findCtaPorEmpresa(listaDeEmpresas,desde, hasta, empresa);
		//ArrayList<String> nombreCuentas = (ArrayList<String>) cuentas.stream().map(unaCuenta->unaCuenta.getNombre()).collect(Collectors.toList());
		return cuentas;
	}
public ArrayList<String> getNombresCuentasPorPeriodo(String desde, String hasta, String empresa) 
{
	DAOjson daojson= new DAOjson();
	//RepositorioDeEmpresas repoEmpresas= new RepositorioDeEmpresas(daojson);
	ArrayList<Empresa> listaDeEmpresas= daojson.getAll();
	ArrayList<Cuenta> cuentas = this.findCtaPorEmpresa(listaDeEmpresas,desde, hasta, empresa);
	ArrayList<String> nombreCuentas = (ArrayList<String>) cuentas.stream().map(unaCuenta->unaCuenta.getNombre()).collect(Collectors.toList());
	return nombreCuentas;
}
public ArrayList<String> getAllNombreCuentas()
{	
	DAOjson daojson= new DAOjson();
	ArrayList<Empresa> listaDeEmpresas= daojson.getAll();
	ArrayList<String> cuentas=new ArrayList<String>() ;
	listaDeEmpresas.stream().map(empresa->empresa.getCuentas()).flatMap(lstCuentas->lstCuentas.stream()).map(cuenta->cuenta.getNombre()).forEach(unaC->cuentas.add(unaC));;
	return cuentas;
	
}

//public ArrayList<Cuenta> findCtaPor(Empresa empresa,String periodoDesde,String periodoHasta)
//{
//	
//}
public List<String> getPeriodosDesde()
{
	return this.getPeriodo().stream().map(p->p.getDesde()).collect(Collectors.toList());
}
public List<String> getPeriodosHasta()
{
	return  this.getPeriodo().stream().map(p->p.getHasta()).collect(Collectors.toList());
}

public boolean perteneceALasCuentas(String cuenta,String desde, String hasta, String empresa)
{
	ArrayList<String> ctas = this.getNombresCuentasPorPeriodo(desde,hasta, empresa);
	return ctas.contains(cuenta);
}

public int obtenerValor(String cuenta, String desde, String hasta, String empresa) 
{
	int valor=0;
	DAOjson daojson= new DAOjson();
	ArrayList<Empresa> listaDeEmpresas= daojson.getAll();
	ArrayList<Cuenta> periodosPorCuenta = this.findCtaPorEmpresa(listaDeEmpresas, desde, hasta, empresa);
	for(int i=0;i<periodosPorCuenta.size();i++)
	{
		List<Periodo> periodos=periodosPorCuenta.get(i).getPeriodo();

	//System.out.println(fecha);	
			for(int j=0;j<periodos.size();j++)
			{
				if(periodos.get(j).getHasta().equals((hasta)) && periodos.get(j).getDesde().equals((desde)))
				{
					//System.out.println(periodos.get(j).getValorCuenta());
					valor=periodos.get(j).getValorCuenta();
				}
			}

	}
	return valor;
}

}