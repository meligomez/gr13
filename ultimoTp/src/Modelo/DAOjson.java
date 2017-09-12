package Modelo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Controller.Cuenta;
import Controller.Empresa;
import Controller.Indicador;
import Controller.Periodo;

/*
 * @Author :Grupo 13
 */

public class DAOjson implements DAOEmpresa {
	

	private String filePath;
	private Gson myGson;
	private BufferedWriter bufferToWrite;
	private ArrayList<Empresa> listaDeEmpresas;

	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}
	
	public DAOjson() {
		super();
		this.myGson = new Gson();
		listaDeEmpresas = new ArrayList<Empresa>();
	}
	

//Crea el archivo .json en el disco D:
	public void addAllStruct() 
	{
		//CREA al json
		 ArrayList<Empresa> listaDeEmpresas = new  ArrayList<Empresa>();
		 
		 Cuenta cuenta1 = new Cuenta();		 
		 Cuenta cuenta2 = new Cuenta();
		 
		 cuenta1.setNombre("IngresoDeOperacionesDiscontinuas");
		 cuenta2.setNombre("IngreosDeOperacionesContinuas");
		 
		 ArrayList<Periodo> periodos1 = new ArrayList<Periodo>();
		 ArrayList<Periodo> periodos2 = new ArrayList<Periodo>();
		 Periodo per1 = new Periodo();
		 per1.setDesde("12/03/2014");
		 per1.setHasta("12/03/2014");
		 per1.setValorCuenta(1222220);
		 
		 Periodo per2 = new Periodo();
		 per2.setDesde("16/09/2013");
		 per2.setHasta("12/12/2013");
		 per2.setValorCuenta(2000000);
		 
		 periodos1.add(per1);periodos2.add(per2);
		 
		 cuenta1.setPeriodos(periodos1);
		 cuenta2.setPeriodos(periodos2);
		 
		 ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		 lista.add(cuenta1);lista.add(cuenta2);
		 //empresaP.setCuentas(lista);
		 
		 Empresa oEmpresa1=new Empresa();
		  oEmpresa1.setNombre("Facebook");
		  //agrego las cuentas pertenecientes a esa empresa
		  ArrayList<Cuenta> listaDeCuenta = new  ArrayList<Cuenta>();
		  Cuenta cta1Emp1=new Cuenta();
		  cta1Emp1.setNombre("EBITDA");
		  ArrayList<Periodo> listaDePeriodos = new  ArrayList<Periodo>();
		  Periodo cta1Emp1Per1=new Periodo();
		  cta1Emp1Per1.setValorCuenta(1541);
		  cta1Emp1Per1.setDesde("01/01/2010");
		  cta1Emp1Per1.setHasta("30/06/2010");
		  listaDePeriodos.add(cta1Emp1Per1);
		  cta1Emp1.setPeriodos(listaDePeriodos);
		  
		  
		  Cuenta cta2Emp1=new Cuenta();
		  cta2Emp1.setNombre("FDS");
		  ArrayList<Periodo> listaDePeriodos2 = new  ArrayList<Periodo>();
		  Periodo cta2Emp1Per2=new Periodo();
		  cta2Emp1Per2.setValorCuenta(1168);
		  cta2Emp1Per2.setDesde("01/01/2010");
		  cta2Emp1Per2.setHasta("30/06/2010");
		  listaDePeriodos2.add(cta2Emp1Per2);
		  cta2Emp1.setPeriodos(listaDePeriodos2);
		  
		  
//		  Cuenta  cta3Emp1=new Cuenta();
//		  cta3Emp1.setNombre("FreeCashFlow");
//		  cta3Emp1.setValor(12232);
//		  cta3Emp1.setPeriodo(2010);
//		  
//		  Cuenta  cta4Emp1=new Cuenta();
//		  cta4Emp1.setNombre("IngresoNetoOperacDisc");
//		  cta4Emp1.setValor(12223);
//		  cta4Emp1.setPeriodo(2011);
//		  
		  listaDeCuenta.add(cta1Emp1);  
		  listaDeCuenta.add(cta2Emp1);
//		  listaDeCuenta.add( cta3Emp1);
//		  listaDeCuenta.add( cta4Emp1);
//		
		  oEmpresa1.setCuentas(listaDeCuenta);
		  

		  //-------------------- 
		  //otraEmpresa
		  
		  Empresa oEmpresa2=new Empresa();
		  oEmpresa2.setNombre("Twitter");
		  //agrego las cuentas pertenecientes a esa empresa
		  ArrayList<Cuenta> listaDeCuenta3 = new  ArrayList<Cuenta>();
		  Cuenta cta1Emp2=new Cuenta();
		  cta1Emp2.setNombre("Free Cash Flow");
		  ArrayList<Periodo> listaDePeriodos3 = new  ArrayList<Periodo>();
		  Periodo cta1Emp2Per1=new Periodo();
		  cta1Emp2Per1.setValorCuenta(1541);
		  cta1Emp2Per1.setDesde("01/06/2015");
		  cta1Emp2Per1.setHasta("30/12/2015");
		  listaDePeriodos3.add(cta1Emp2Per1);
		  cta1Emp2.setPeriodos(listaDePeriodos3);
		  //		  
//		  Cuenta cta2Emp2=new Cuenta();
//		  cta2Emp2.setNombre("FreeCashFlow");
//		  cta2Emp2.setValor(1457);
//		  cta2Emp2.setPeriodo(2014);
//		   
		  listaDeCuenta3.add(cta1Emp2);
//		  listaDeCuenta2.add(cta2Emp2);
		  oEmpresa2.setCuentas(listaDeCuenta3);
//		  

		  
		  listaDeEmpresas.add(oEmpresa1);

		  listaDeEmpresas.add(oEmpresa2);
		  Gson gson = new Gson();
		  
		  // Convierte un objeto de java en un JSON,
		  // retorna un json con un formato sting 
		  String json = gson.toJson(listaDeEmpresas);
		  
		  try {
		//   write convierte un json en un archivo llamado "modeloGSON.json"
		   FileWriter writer = new FileWriter("C:modeloGSON.json");
		   writer.write(json);
		   writer.close();
		  
		  } catch (IOException e) {

		   e.printStackTrace();
		  }

		  System.out.println(json);
		  
	 }

	public void add(Empresa empresa) throws IOException{
		String empresaSerializado = myGson.toJson(empresa);
		this.writeJson(empresaSerializado);
	}
	
	private void writeJson(String empresaSerialized) throws IOException{
		this.bufferToWrite = new BufferedWriter(new FileWriter(this.filePath, true));
		this.bufferToWrite.append(empresaSerialized);
		this.bufferToWrite.close();
	}

//Me trae una lista de Empresas desde el json de la ruta donde fue creado!	
	public ArrayList<Empresa> getAll()
	{
	
		//DEBO FILTRAR POR EL AÑO!!!
		Gson gson = new Gson();
		ArrayList<Empresa> listaDeEmpresas= new ArrayList<Empresa>();
		try {
		
			  
		 BufferedReader br = new BufferedReader(new FileReader("C:modeloGSON.json"));


		final Type tipoListaEmpresa = new TypeToken<List<Empresa>>(){}.getType();
		listaDeEmpresas = gson.fromJson(br,tipoListaEmpresa);

		 return listaDeEmpresas;		  
		 } catch (IOException e) {
			 e.printStackTrace();
			 return null;
		 }
		
	}
	

	public void escribirArchivo(ArrayList<Empresa> lista){			
		String json = myGson.toJson(lista);		
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(json);
			writer.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	//tengo que modificarlocon stream
	public int findEmpresa(String nombre){
		this.listaDeEmpresas = this.getAll();		
		for (int i = 0; i < listaDeEmpresas.size(); i++){
			if(listaDeEmpresas.get(i).getNombre().equals(nombre)){				
				return i;
			}
		}
		return 0;
	}

	public void delete(Empresa empresa){
		listaDeEmpresas = this.getAll();
		int a = this.findEmpresa(empresa.getNombre());
		try{
			listaDeEmpresas.remove(a);
			escribirArchivo(listaDeEmpresas);
		}catch(Exception e){
			e.getMessage();
		}

	}


	public void update(Empresa empresa,String nombre){
		this.listaDeEmpresas = this.getAll();		
		try{
			int a = this.findEmpresa(empresa.getNombre());
			listaDeEmpresas.get(a).setNombre(nombre);
			this.escribirArchivo(listaDeEmpresas);
		}catch(Exception e){
			e.getMessage();
		}		
	}	 
	
	

public List<Empresa> find(String desde,String hasta)
{		//Find: encuentra las empresas que tengan cuentas en ese periodo
	Gson gson = new Gson();
	try 
	 {
		BufferedReader br = new BufferedReader(new FileReader("C:modeloGSON.json"));
		final Type tipoListaEmpresa = new TypeToken<List<Empresa>>(){}.getType();
		List<Empresa> empresas =  gson.fromJson(br,tipoListaEmpresa);
	    List<Empresa> result = empresas.stream()   // Convierte la lista en un Stream
		                .filter(empresa -> this.pertenecePeriodo(desde,hasta,empresa.getCuentas()))  //Filtro segun criterio de Periodo
		                .collect(Collectors.toList());       // Convierte el Stream en lista de nuevo

      	System.out.println(result.get(0).getNombre());

        return result;
	} 
	catch (IOException e) 
	{
		 e.printStackTrace();
	}
		return null;
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

//Hacer con stream()
public ArrayList<Cuenta> findCtaPorEmpresa(ArrayList<Empresa> listaDeEmpresas,String fecha, String empresa)
{
	ArrayList<Cuenta> cuentasPorEmpr= new ArrayList<Cuenta>();
	//de todas las empresas con cuentas en ese periodo selecciona la empresa pedida y sus cuentas
	for (int i = 0; i < listaDeEmpresas.size(); i++)
	{
		if(listaDeEmpresas.get(i).getNombre().equals(empresa))
		{
			ArrayList<Cuenta> cuentasPorEmpresa = listaDeEmpresas.get(i).getCuentas();
			for(int j=0;j<cuentasPorEmpresa.size();j++)
			{
				//if(cuentasPorEmpresa.get(j).getPeriodo().contains(anio))
				{
					ArrayList<Periodo> periodosPorCuenta=cuentasPorEmpresa.get(j).getPeriodo();
					for(int k=0;k<periodosPorCuenta.size();k++)
					{
						cuentasPorEmpr.add(cuentasPorEmpresa.get(i));
					}
					
				}
			}
		}
	}
	return cuentasPorEmpr;
}



	//metodo para find (buscar en el json segun algun criterio y devolver los objetos)
}
