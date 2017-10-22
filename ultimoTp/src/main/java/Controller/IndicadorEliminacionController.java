package Controller;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorEliminacionController {
private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicioIndicadorEliminacion(Request req, Response res){
		
		return new ModelAndView(model, "indicadorEliminacion.hbs");
	}
}
