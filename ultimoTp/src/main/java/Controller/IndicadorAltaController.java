package Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Entity.Cuenta;
import Entity.Empresa;
import Entity.Indicador;
import Entity.Usuario;
import Modelo.DAOGlobalMYSQL;
import Modelo.DAOIndicador;
import Modelo.DAOIndicadorMYSQL;
import Modelo.DAOUsuario;
import Modelo.DAOUsuarioMYSQL;
import db.EntityManagerHelper;
import parserIndicadores.GrammarIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorAltaController {
private Map<String, Object> model=new HashMap<>();
	
public ModelAndView inicioIndicadorAlta(Request req, Response res){
	DAOGlobalMYSQL modelSuper =new DAOGlobalMYSQL();
	List<Cuenta> cuentas=modelSuper.getAllCuentas();
	model.put("cuentas", cuentas);
	return new ModelAndView(model, "indicadorAlta.hbs");
}


public ModelAndView verificarFormula(Request req , Response res)
{
	Indicador indicador= new Indicador();
	String sentence = req.queryParams("hdnForm");
	System.out.println("aca viene :"+sentence );
	indicador.setFormula(sentence);
	indicador.setSePuedeBorrar(false);
	Usuario u=new Usuario();
	//DAOUsuarioMYSQL daousuario= new DAOUsuarioMYSQL();
	DAOGlobalMYSQL daousuario = new DAOGlobalMYSQL();
	
	indicador.setUsuario((daousuario).findPorId(1));

	Empresa empresa = null ;
	if(empresa.sintaxisCorrecta(sentence.replace(" ","")))
	{
		model.put("formulaCorrecta", sentence);
        return new ModelAndView(model, "indicadorAlta.hbs");
	}
	else
	{
		
		 return new ModelAndView(model, "errorSintaxis.hbs");
	
	}
	

}

public ModelAndView darAlta(Request req, Response res) throws IOException{
	Indicador indicador= new Indicador();
	String nombreIndicador = req.queryParams("nombreIndicador");
	String sentence = req.queryParams("hdnFormAlta");
	System.out.println(sentence);
	indicador.setFormula(sentence);
	indicador.setNombre(nombreIndicador);
	indicador.setSePuedeBorrar(false);
	Usuario u=new Usuario();
	//DAOUsuarioMYSQL daousuario= new DAOUsuarioMYSQL();
	DAOGlobalMYSQL daousuario = new DAOGlobalMYSQL();
	
	indicador.setUsuario((daousuario).findPorId(1));

     DAOGlobalMYSQL daoindicador = new DAOGlobalMYSQL();
     daoindicador.add(indicador);
     
	return new ModelAndView(model, "altaIndicadorOk.hbs");
}


	public Usuario findPorNombre(String nombre) {
		EntityManager em = EntityManagerHelper.entityManager();
		
		Usuario use = (Usuario) em.createNativeQuery(
				  "select * from inversiones.usuario  where nombre = :username", Usuario.class).
				  setParameter("username", nombre).getSingleResult();
		return use;
	}
	
	private List<Indicador> findUsuario(String usuario) {
		  EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		  CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		  CriteriaQuery<Indicador> criteriaQuery = criteriaBuilder.createQuery(Indicador.class);
		  Root<Indicador> root = criteriaQuery.from(Indicador.class);
		  criteriaQuery.select(root);
		  ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
		  criteriaQuery.where(criteriaBuilder.equal(root.get("usuario"), params));
		  TypedQuery<Indicador> query = entityManager.createQuery(criteriaQuery);
		  query.setParameter(params, usuario);
		  return query.getResultList();
	}
	
}
