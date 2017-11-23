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

import Entity.CondicionTaxativa;
import Entity.Empresa;
import Entity.Metodologia;

public class DAOmetodologiaJson implements DAOmetodologia{


		private Gson myGson;
		private BufferedWriter bufferToWrite;
		private ArrayList<Metodologia> listaDeMetodologiaes;	
		private static DAOmetodologiaJson instance = null;
		
		public DAOmetodologiaJson() {
			super();
			this.myGson = new Gson();
			listaDeMetodologiaes = new ArrayList<Metodologia>();
		}
		
		public static DAOmetodologiaJson getInstance() {
			if(instance==null){
				instance = new DAOmetodologiaJson();
			}
			return instance;
		}
		
		public void addAllStruct() 
		{
			ArrayList<Metodologia> listaDeMetodologiaes= new ArrayList<Metodologia>();
			Metodologia metodologia=new Metodologia();
			metodologia.setNombre("Metodologia1");
			
			ArrayList<CondicionTaxativa> condiciones= new ArrayList<CondicionTaxativa>();
			CondicionTaxativa condicion1= new CondicionTaxativa();
			condicion1.setIndicadorOCuenta("EBITDA");
			condicion1.setExpresion("Menor");
			condicion1.setValorAComparar(1900);

			CondicionTaxativa condicion2= new CondicionTaxativa();
			condicion2.setIndicadorOCuenta("FDS");
			condicion2.setExpresion("Igual");
			condicion2.setValorAComparar(1168);
			
			condiciones.add(condicion1);
			condiciones.add(condicion2);
			
			metodologia.setCondiciones(condiciones);
			
			listaDeMetodologiaes.add(metodologia);

			 //-------------------- 
			 //otraMetodologia
			 Metodologia oMetodologia2=new Metodologia();
			 oMetodologia2.setNombre("Metodologia 2");
			ArrayList<CondicionTaxativa> condicionesM2= new ArrayList<CondicionTaxativa>();
			CondicionTaxativa condicion1M2= new CondicionTaxativa();
			condicion1M2.setIndicadorOCuenta("FDS");
			condicion1M2.setExpresion("Mayor");
			condicion1M2.setValorAComparar(5000);
			condicionesM2.add(condicion1M2);
			oMetodologia2.setCondiciones(condicionesM2); 
			
			
			 listaDeMetodologiaes.add(oMetodologia2);
			  Gson gson = new Gson();
			  
			  // Convierte un objeto de java en un JSON,
			  // retorna un json con un formato sting 
			  String json = gson.toJson(listaDeMetodologiaes);
			  
			  try {
			//   write convierte un json en un archivo llamado "modeloGSON.json"
			   FileWriter writer = new FileWriter("C:\\Home\\MetodologiaGSON.json");
			   writer.write(json);
			   writer.close();
			  
			  } catch (IOException e) {

			   e.printStackTrace();
			  }

			  System.out.println(json);
			  
		 }


		@Override
		public void add(Metodologia metodologia) throws IOException {
			String MetodologiaSerializado = myGson.toJson(metodologia);
			this.writeJson(MetodologiaSerializado);		
		}

		private void writeJson(String MetodologiaSerializado) throws IOException{
			this.bufferToWrite = new BufferedWriter(new FileWriter("C:\\Home\\MetodologiaGSON.json", true));
			this.bufferToWrite.append(MetodologiaSerializado);
			this.bufferToWrite.close();		
		}
		
		public int findMetodologia(String nombre){
			this.listaDeMetodologiaes = this.getAll();		
			for (int i = 0; i < listaDeMetodologiaes.size(); i++){
				if(listaDeMetodologiaes.get(i).getNombre().equals(nombre)){				
					return i;
				}
			}
			return 0;
		}
		
		public void escribirArchivo(ArrayList<Metodologia> lista){			
			String json = myGson.toJson(lista);		
			try {
				FileWriter writer = new FileWriter("C:\\Home\\MetodologiaGSON.json");
				writer.write(json);
				writer.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}


		public void delete(String nombre) {
		
			listaDeMetodologiaes = this.getAll();
			int a = this.findMetodologia(nombre);
			try{
				listaDeMetodologiaes.remove(a);
				escribirArchivo(listaDeMetodologiaes);
			}catch(Exception e){
				e.fillInStackTrace();
			}
		}

		@Override
		public void update(Metodologia metodologia,String name) {
			// TODO Auto-generated method stub
			
		}

		public ArrayList<Metodologia> getAll()
		{
		
			//DEBO FILTRAR POR EL A�O!!!
			Gson gson = new Gson();
			ArrayList<Metodologia> listaDeMetodologiaes= new ArrayList<Metodologia>();
			try {
				  
			 BufferedReader br = new BufferedReader(new FileReader("C:\\Home\\MetodologiaGSON.json"));


			final Type tipoListaMetodologia = new TypeToken<List<Metodologia>>(){}.getType();
			listaDeMetodologiaes = gson.fromJson(br,tipoListaMetodologia);

			 return listaDeMetodologiaes;		  
			 } catch (IOException e) {
				 e.printStackTrace();
				 return null;
			 }
			
		}
		
		@Override
		public List<Metodologia> find(String nombre) {
			Gson gson = new Gson();
			try 
			 {
				BufferedReader br = new BufferedReader(new FileReader("C:\\Home\\MetodologiaGSON.json"));
				final Type tipoListaMetodologia = new TypeToken<List<Metodologia>>(){}.getType();
				List<Metodologia> metodologiaes =  gson.fromJson(br,tipoListaMetodologia);
			    List<Metodologia> result = metodologiaes.stream()   // Convierte la lista en un Stream
				                .filter(metodologia -> metodologia.getNombre().equals(nombre))  //Filtro segun criterio de nombre
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
		
		
		@Override
		public void writeArray(ArrayList<Metodologia> lista) throws IOException{
			Gson gson = new Gson();						  
			String j = gson.toJson(lista);
			  
			try {					
				FileWriter writer = new FileWriter("C:\\Home\\MetodologiaGSON.json");
				writer.write(j);
				writer.close();
				}
			catch (IOException e) {
				e.printStackTrace();
			}
		}


	
}