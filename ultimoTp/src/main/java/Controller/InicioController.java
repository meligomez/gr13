package Controller;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicio(Request req, Response res){
		//SupermecadoModel modelSuper = SupermecadoModel.getInstance();
		//model.put("supermercados", modelSuper.getAll());
		return new ModelAndView(model, "base.hbs");
	}
}