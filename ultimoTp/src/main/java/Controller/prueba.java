package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Modelo.DAOmetodologiaJson;
import Modelo.RepositorioDeMetodologia;

public class prueba {

	public static void main(String[] args) {
		DAOmetodologiaJson dao = new DAOmetodologiaJson();
		RepositorioDeMetodologia repoMetodologia = new RepositorioDeMetodologia();
				
		ArrayList<Metodologia> listaDeMetodologiaes= new ArrayList<Metodologia>();
		
		try {
			listaDeMetodologiaes=repoMetodologia.getAllmetodologias();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Tamaño "+ listaDeMetodologiaes.size());
		
		Metodologia metodologia=new Metodologia();
		metodologia.setNombre("Metodologia3");
		
		ArrayList<CondicionTaxativa> condiciones= new ArrayList<CondicionTaxativa>();
		CondicionTaxativa condicion1= new CondicionTaxativa();
		condicion1.setIndicadorOCuenta("EBITDA");
		condicion1.setExpresion("Menor");
		condicion1.setValorAComparar(1900);
		
		condiciones.add(condicion1);
		
		metodologia.setCondiciones(condiciones);
		
		Metodologia metodologia1=new Metodologia();
		metodologia1.setNombre("Metodologia4");
		
		ArrayList<CondicionTaxativa> condicione= new ArrayList<CondicionTaxativa>();
		CondicionTaxativa condicion= new CondicionTaxativa();
		condicion.setIndicadorOCuenta("EBITDA");
		condicion.setExpresion("Menor");
		condicion.setValorAComparar(1900);
		
		condicione.add(condicion1);
		
		metodologia1.setCondiciones(condicione);
		
		listaDeMetodologiaes.add(metodologia1);
		listaDeMetodologiaes.add(metodologia);
		
		try {
			repoMetodologia.writeArray(listaDeMetodologiaes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*Gson gson = new Gson();
		String json = gson.toJson(listaDeMetodologiaes);
		  
		  String j = gson.toJson(metodologia);*/
		  
		 /* try {
				//   write convierte un json en un archivo llamado "modeloGSON.json"
				   FileWriter writer = new FileWriter("C:\\Home\\MetodologiaGSON.json");
				   writer.write(j);
				   
				   writer.close();
				  
				  } catch (IOException e) {

				   e.printStackTrace();
				  }*/

	}
}
