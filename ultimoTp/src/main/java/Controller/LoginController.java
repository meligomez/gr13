package Controller;

import java.util.HashMap;
import java.util.Map;

import Entities.Usuario;
import Modelo.DAOUsuarioJson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	private Map<String, Object> model=new HashMap<>();
		
		public ModelAndView inicio(Request req, Response res){
			DAOUsuarioJson usuariojson= new DAOUsuarioJson();
			usuariojson.addAllStruct();
			return new ModelAndView(model, "login.hbs");
		}
		
		public ModelAndView verificarUsuario(Request req, Response res)
		{
			DAOUsuarioJson usuariojson= new DAOUsuarioJson();
			usuariojson.addAllStruct();
			String usuarioBuscado = req.queryParams("Usuario");
			Usuario usuario = usuariojson.get(usuarioBuscado);
			model.put("usuario", usuario);
			return new ModelAndView(model,"inicio.hbs");
		}
}
