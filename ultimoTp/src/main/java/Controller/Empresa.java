package Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author : Grupo 13
 */
public class Empresa {
private String nombre;
 ArrayList<Cuenta> cuentas;

public String getNombre() {
	return this.nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
public ArrayList<Cuenta> getCuentas() 
{
	return cuentas;
}

public void setCuentas(ArrayList<Cuenta> cuenta) 
{
	this.cuentas=cuenta;
}

public ArrayList<String> allNombresEmpresa(ArrayList<Empresa> listaDeEmpresas )
{	
	 ArrayList<String> nombres = new ArrayList<String>();
	 nombres.addAll(listaDeEmpresas.stream().map(empresa->empresa.getNombre()).collect(Collectors.toList()));
	 return nombres;
	 
}
//tengo que modificarlocon stream
public int findEmpresa(String nombre,ArrayList<Empresa> listaDeEmpresas)
{	
	for (int i = 0; i < listaDeEmpresas.size(); i++){
		if(listaDeEmpresas.get(i).getNombre().equals(nombre)){				
			return i;
		}
	}
	
	return 0;
}

public Boolean pertenecePeriodo(String desde, String hasta,ArrayList<Cuenta> ctas)
{
    Boolean result = ctas.stream()   // Convierte la lista en un Stream
	                .filter(unaCuenta -> this.periodoEs(desde,hasta,unaCuenta.getPeriodo())).count()>0;
	return result;
}

public Boolean periodoEs(String desde, String hasta,ArrayList<Periodo> ctas)
{
    Boolean result = ctas.stream()   // Convierte la lista en un Stream
	                .filter(unPeriodo -> unPeriodo.getDesde().equals(desde) && unPeriodo.getHasta().equals(hasta)).count()>0;
	return result;
}

public ArrayList<Empresa> empresasQuePertenecen(List<Empresa> empresas,String desde,String hasta)
{
	  ArrayList<Empresa> result = (ArrayList<Empresa>) empresas.stream()   // Convierte la lista en un Stream
	          .filter(empresa -> empresa.pertenecePeriodo(desde,hasta,empresa.getCuentas()))  //Filtro segun criterio de Periodo
	          .collect(Collectors.toList());       // Convierte el Stream en lista de nuevo
	  return result;
}


}
