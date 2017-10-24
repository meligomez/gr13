package Controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import Entity.Indicador;
import Entity.Usuario;
import Modelo.DAOIndicador;
import Modelo.DAOIndicadorMYSQL;
import Modelo.DAOUsuario;
import Modelo.DAOUsuarioMYSQL;
import parserIndicadores.GrammarIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorAltaController {
private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicioIndicadorAlta(Request req, Response res){
		return new ModelAndView(model, "indicadorAlta.hbs");
	}
	public ModelAndView verificarAltaIndicador(Request req, Response res){
		Indicador indicador= new Indicador();
		String nombreIndicador = req.queryParams("nombreIndicador");
		String sentence = req.queryParams("formula");
		indicador.setFormula(sentence);
		indicador.setNombre(nombreIndicador);
		indicador.setSePuedeBorrar(false);
		Usuario u=new Usuario();
		DAOUsuarioMYSQL daousuario= new DAOUsuarioMYSQL();
		
		indicador.setUsuario(daousuario.findPorId(1));
		
		GrammarIndicadores parser = null;
        // Put parens around sentence so that parser knows scope
        sentence = "(" + sentence + ")";
        InputStream is = new ByteArrayInputStream(sentence.getBytes());
        if(parser == null)  parser = new GrammarIndicadores(is);
        else GrammarIndicadores.ReInit(is);
        try
        {
          switch (GrammarIndicadores.start())
          {
            case 0 :
            	System.out.println("La formula esta Sintacticamente correcta.");
            	DAOIndicador daoindicador= new DAOIndicadorMYSQL();
            	daoindicador.add(indicador);
            	//GrammarIndicadores.symbol();
            	//IF del se puede aplicar
            	//si esta todo OK aplicateA
            break;
            default :
            break;
          }
        }
        catch (Exception e)
        {
        System.out.println("Error de Sintaxis.");
        }
        catch (Error e)
        {
        System.out.println("Error de Sintaxis.");
        }
        finally
        {
          
        }
        return new ModelAndView(model, "verificarAlta.hbs");
	}
	
}
