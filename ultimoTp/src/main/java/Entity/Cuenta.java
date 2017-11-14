package Entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
import db.EntityManagerHelper;

@Entity
@Table(name = "cuenta")
public class Cuenta implements Entidad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	String nombre;
	private int valor;
	private String valorCTA;
	private ArrayList<Integer> valoresDeCuentas;
	private ArrayList<String> cuentaLista;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="cuentas")
	private List<EmpresaCuenta> cuentas ;
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Empresa> empresas;
	
	//@OneToMany(mappedBy="cuenta",cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	 
	public Cuenta(){
		super();
		this.cuentas=new LinkedList<>();
		//periodos = new LinkedList<>();
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
	/*
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
	*/
public int obtenerValorIndicado (String cuenta, String desde, String hasta, String empresa){
		
	if (perteneceALasCuentas(cuenta,desde,hasta,empresa)){
		return obtenerValor(cuenta,desde,hasta,empresa);
	}
	
	return 0;
}
public int obtenerValor(String cuenta, String desde, String hasta, String empresa) 
{
	return buscarValorINmysql(cuenta, desde,hasta,empresa);
}

public int buscarValorINmysql(String cuenta, String desde, String hasta, String empresa){
	 
	/* buscar en mySql el valor de la cuenta en un periodo especifico 
		y de tal empresa
	*/
	EntityManager em = EntityManagerHelper.entityManager();
	return  (int) em.createNativeQuery("Select valorCuenta from periodo p join cuenta_empresa ce on(ce.id=p.cuenta_empresa) join empresa e on(e.id=ce.empresas_id) join cuenta c on(c.id=ce.cuentas_cuenta_id) where c.nombre='"+cuenta+"' and p.desde ='"+desde+"' and p.hasta ='"+hasta+"' and e.nombre ='"+nombre+"'").getSingleResult();
}
public ArrayList<String> cuentasDeLaFormula(String formula)
{
	
	int i=0;
	String word;
	StringTokenizer elementos,subelementos;
	ArrayList<String> palabra= new ArrayList<String>();
	elementos = new StringTokenizer(formula,"(+/*-)");
	while(elementos.hasMoreTokens()){
	  word = elementos.nextToken();
	  i=1;
	  subelementos = new StringTokenizer(word,",");
	  while(subelementos.hasMoreTokens()){
	    palabra.add( subelementos.nextToken());
	    
	    i++;
	  }
	}
	return palabra;
}

public ArrayList<Integer> getValorFormula(String formula,String desde, String hasta, String empresa){
		
	cuentaLista= this.cuentasDeLaFormula(formula);
	 for(int x=0;x<cuentas.size();x++) {  // recorro [x,y]
			valorCTA = cuentaLista.get(x); // valor = x
			int valor1 = this.obtenerValorIndicado(valorCTA, desde, hasta, empresa);  //devuelve el valor de x, ej: x=10
			valoresDeCuentas.add(valor1); //lo agrego a la lista de valores
			 //  System.out.println(cuentas.get(ValoresDeCuentas));
			}
	
	return valoresDeCuentas;

}



}