package Controller;


	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.Map;

import Entity.Empresa;
import Entity.Periodo;
import Modelo.DAOGlobalMYSQL;
import Modelo.DAOjson;
	import Modelo.DAOmetodologiaJson;
	import spark.ModelAndView;
	import spark.Request;
	import spark.Response;

	public class MetodologiaListaEmpresaController {
		private Map<String, Object> model=new HashMap<>();
		
		public ModelAndView inicioMetodologiaListaEmpresas(Request req, Response res){
			
			DAOGlobalMYSQL<Periodo> modelPeriodo = new DAOGlobalMYSQL<Periodo>(Periodo.class);
			DAOGlobalMYSQL<Empresa> modelEmpresa = new DAOGlobalMYSQL<Empresa>(Empresa.class);
			
			
			model.put("empresas", modelEmpresa.getAllEmp());
			model.put("periodosDesde", modelPeriodo.getAllPeriodos());
			model.put("periodosHasta", modelPeriodo.getAllPeriodos());	
			
			return new ModelAndView(model, "metodologiaListaEmpresas.hbs");
		}
		
		public ModelAndView consultaMetodologiaListaEmpresas(Request req, Response res){
			
			return new ModelAndView(model, "consultaMetodologiasListaEmpresas.hbs");
		
		}

	
}
