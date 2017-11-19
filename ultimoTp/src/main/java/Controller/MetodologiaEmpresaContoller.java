package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.persistence.EntityManager;

import Entity.Metodologia;
import Entity.Usuario;
import Modelo.DAOjson;
import Modelo.DAOmetodologiaJson;
import db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaEmpresaContoller {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicioMetodologia(Request req, Response res){
		
		String nombre_usuario = req.session().attribute("usuario");
		String empresaSeleccionada = req.queryParams("empresa");
		String desde = req.queryParams("formula");
		String hasta = req.queryParams("hasta");
		
		Usuario usuarioActual = findPorNombre(nombre_usuario);
		
//		Metodologia metodologiaSeleccionada=new Metodologia();
//		metodologiaSeleccionada.setCondiciones(usuarioActual.getListaCondiciones());
//		
//		boolean resultado;
//		//cumpleCondiciones(string empresa,string desde,string hasta,metodologiaseleccionada)
//		resultado=metodologiaSeleccionada.cumpleCondiciones(empresaSeleccionada,desde,hasta,metodologiaSeleccionada);
//		
//		if(resultado)
//			
//			//aca se deberia mostrar una pantalla
//			System.out.println("Empresa que sirve le conviene invertir");
//		else
//			//same aca una pantalla
//			System.out.println("NO le conviene invertir");
		
		return new ModelAndView(model, "metodologiaEmpresa.hbs");
	}
	public Usuario findPorNombre(String nombre) {
		EntityManager em = EntityManagerHelper.entityManager();
		
		Usuario use = (Usuario) em.createNativeQuery(
				  "select * from redinversiones.usuario  where nombre = :username", Usuario.class).
				  setParameter("username", nombre).getSingleResult();
		return use;
	}
}
