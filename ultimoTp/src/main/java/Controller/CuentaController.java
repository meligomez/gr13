package Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Modelo.DAOjson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentaController {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicioCuenta(Request req, Response res){
		DAOjson modelSuper = DAOjson.getInstance();
		modelSuper.addAllStruct();
		model.put("empresas", modelSuper.getAllEmp());
		return new ModelAndView(model, "cuentas.hbs");
	}
	public ModelAndView listarCuentas(Request req, Response res){
		DAOjson modelSuper = DAOjson.getInstance();
		modelSuper.addAllStruct();
		String empresaBuscada = req.queryParams("empresa");
		Empresa empresa = modelSuper.get(empresaBuscada);
		model.put("empresa", empresa);
		
		return new ModelAndView(model, "cuentas.hbs");
	}
	public ModelAndView consultaCuenta(Request req, Response res){
		DAOjson modelSuper = DAOjson.getInstance();
		modelSuper.addAllStruct();
		String market = req.params(":empresa");
		String periodoDesde = req.params(":periodoDesde");
		String periodoHasta = req.params(":periodoHasta");
		
//		Empresa empresa = modelSuper.findNombreEmpresa(market);
//		model.clear();
//		model.put("empresa", empresa);
//		model.put("cuentas", empresa.getCuentas());
		return new ModelAndView(model, "cuentas.hbs");
	}
}