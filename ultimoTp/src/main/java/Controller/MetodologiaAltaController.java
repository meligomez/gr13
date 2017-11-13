package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Entity.CondicionTaxativa;
import Entity.Metodologia;
import Modelo.DAOGlobalMYSQL;
import Modelo.DAOmetodologiaJson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaAltaController {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicioMetodologiaAlta(Request req, Response res){
		return new ModelAndView(model, "metodologiaAlta.hbs");
	}
	
	public ModelAndView altaMetodologia(Request req, Response res){
		
		//GUARDA METODOLOGIA TAXATIVA
		CondicionTaxativa condicionTaxativa = new CondicionTaxativa();
		String nombre = req.queryParams("nombreMetodologia");
		String condicion = req.queryParams("condicion");
		
		System.out.println("Nombre Metodologia " + nombre + " Cuenta: "+condicion);
		String strar[]=condicion.split(" ");
		
		//--AGREGO CONDICION 
		condicionTaxativa.setIndicadorOCuenta(strar[0]);
		condicionTaxativa.setExpresion(strar[1]);
		int valorAComparar= Integer.parseInt(strar[2]);
		condicionTaxativa.setValorAComparar(valorAComparar);
		
		DAOGlobalMYSQL modelsuper = new DAOGlobalMYSQL();
		//DAOmetodologiaJson modelSuper= DAOmetodologiaJson.getInstance();
		
		ArrayList<CondicionTaxativa> listaCondiciones= new ArrayList<CondicionTaxativa>();
		ArrayList<Metodologia> listaMetodologias = new ArrayList<Metodologia>();
		
		
		//AGREGO CONDICION A LISTACONDICION
		listaCondiciones.add(condicionTaxativa);
		
		System.out.println("Nombre Metodologia " + nombre + " Cuenta: "+ strar[0] + "A comparar: " +strar[1]+" valor: "+valorAComparar);
		
		Metodologia metodologiaAgregada = new Metodologia(nombre,listaCondiciones);
		/*listaMetodologias = modelSuper.getAll();
				
		if(modelSuper.findMetodologia(nombre)== 0){
			try {
				listaMetodologias.add(metodologiaAgregada);
				modelSuper.writeArray(listaMetodologias);				
			} catch (IOException e1) {
				// TODO Auto-generated catch block					
				e1.printStackTrace();
			}
		}*/
		 return new ModelAndView(model, "verificarAlta.hbs");
	}
}
