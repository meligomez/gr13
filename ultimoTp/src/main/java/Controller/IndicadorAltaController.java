package Controller;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorAltaController {
private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicioIndicadorAlta(Request req, Response res){
		
	
		return new ModelAndView(model, "indicadorAlta.hbs");
	}
}
