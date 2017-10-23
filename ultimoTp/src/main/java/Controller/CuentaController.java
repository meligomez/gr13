package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Entity.Cuenta;
import Entity.Empresa;
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
		model.put("periodosDesde", modelSuper.getAllPeriodos());
		model.put("periodosHasta", modelSuper.getAllPeriodos());
		return new ModelAndView(model, "cuentas.hbs");
	}
	public ModelAndView listarCuentas(Request req, Response res){
		DAOjson modelSuper = DAOjson.getInstance();
	
		String empresaBuscada = req.queryParams("empresa");
		Empresa empresa = modelSuper.get(empresaBuscada);
		model.put("empresa", empresa);
		
		return new ModelAndView(model, "cuentas.hbs");
	}
	public ModelAndView consultaCuenta(Request req, Response res){
		DAOjson modelSuper = DAOjson.getInstance();
		String empresaB = req.queryParams("empresa");
		String periodoDesde = req.queryParams("periodoDesde");
		String periodoHasta = req.queryParams("periodoHasta");
		Empresa empresa =modelSuper.findEmpresa(empresaB);
		ArrayList<Cuenta> cuentas=empresa.getCuentas();
		ArrayList<Cuenta> cta2 =new ArrayList<Cuenta>() ;
		for(int i=0;i<  cuentas.size();i++)
		{
			List<Cuenta> ctaPorPeriodo=cuentas.get(i).getCuentasPorPeriodo(periodoDesde,periodoHasta, empresaB);
			for(int j=0;j<ctaPorPeriodo.size();j++)
			{
				cta2.add(ctaPorPeriodo.get(j));
			}
			
		}
		//= (ArrayList<Cuenta>) cuentas.stream().map(cta->cta.getCuentasPorPeriodo(periodoDesde, periodoHasta, empresa.getNombre()));
		model.clear();
		model.put("empresa", empresa);
		model.put("cuentas",cta2);
		return new ModelAndView(model, "consultaCuenta.hbs");
	}
}