package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import Modelo.DAOIndicadorJson;

public class Indicador {
	private String nombre;
	private String formula;
	
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) 
	{
		this.formula = formula;
	}
	
	public ArrayList<String> getAllNombres()
	{
		DAOIndicadorJson dao=new DAOIndicadorJson();
		ArrayList<String> nombres = new ArrayList<String>();
		ArrayList<Indicador> indicadoresPorEmpresa= new ArrayList<Indicador>();
		indicadoresPorEmpresa=dao.getAll();
		
		for(int i=0;i<indicadoresPorEmpresa.size();i++)
		{
			nombres.add(indicadoresPorEmpresa.get(i).getNombre());
		}

		return nombres;
	} 

	
	public int aplicarA(Empresa empresa)
	{
		
		//Optional<Cuenta> a = empresa.getCuentas().stream().findFirst(cuenta1 -> cuenta1.getNombre().equals);
		return 0;
	}
	
	public boolean existeIndicador(String nombre)
	{
		 DAOIndicadorJson dao=new DAOIndicadorJson();
			
		return dao.find(nombre).size()>0;
	}
	public void aplicarA(Empresa empresa,Periodo desde,Periodo hasta) throws IOException
	{
		 DAOIndicadorJson dao=new DAOIndicadorJson();
			
		if(!this.existeIndicador(this.getNombre()))
		{
			Indicador indic= new Indicador();
			indic.setNombre(nombre);
			indic.setFormula(formula);
			dao.add(indic);
		}
		
	}
	public ArrayList<String> elementosDeLaFormula(String formula)
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
	
	public boolean sePuedeAplicar(String formula,String fechaDesde,String fechaHasta,String nombreEmpresa)
	{
		//1.tengo que ver si es un indicador o una cuenta
		//2. si es indicador ver si existe en el json y obtener sus cuentas
		//3. si es una cuenta tengo que ver que todas pertenezcan a la misma empresa 
		//5.de ser OK retornar true
		ArrayList<String> elementos= this.elementosDeLaFormula(formula);
		Cuenta cuenta= new Cuenta();
		ArrayList<String> cuentasFiltradas;
		cuentasFiltradas=(ArrayList<String>) elementos.stream().filter(elemento->cuenta.perteneceALasCuentas(elemento, fechaDesde, fechaHasta, nombreEmpresa)).collect(Collectors.toList());
	//	cuenta.getAllNombreCuentas().stream().allMatch(predicate)(cuentasFiltradas);
//		System.out.println(cuenta.getAllNombreCuentas());
		return true;
//		if(!this.existeIndicador() )
	//	{
			
		//}
	
	}
	



}
