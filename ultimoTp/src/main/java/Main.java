

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import Entity.*;
import db.EntityManagerHelper;
public class Main {

	public static void main(String[] args) {
		EntityManager entityManager= EntityManagerHelper.getEntityManager();

		/*Usuario user = new Usuario();
		user.setNombre("Admin");
		user.setContraseña("100000");
		
		Indicador indicador1 = new Indicador();
		indicador1.setUsuario(user);
		indicador1.setNombre("Indicador1");
		indicador1.setFormula("EBITDA+IngersoNeto");
		indicador1.setSePuedeBorrar(false);
		user.addIndicador(indicador1);*/
		Cuenta cuenta = new Cuenta();
		cuenta.setNombre("KK");

		Periodo per1 = new Periodo();
		per1.setDesde("12/03/2014");
		per1.setHasta("12/03/2014");
		per1.setValorCuenta(1222220);
		per1.setCuenta(cuenta);
		
		Periodo per2 = new Periodo();
		per2.setDesde("16/09/2013");
		per2.setHasta("12/12/2013");
		per2.setValorCuenta(2000000);
		per2.setCuenta(cuenta);
		
		List<Periodo> listaPeriodos=new LinkedList<>();
		listaPeriodos.add(per1);listaPeriodos.add(per2);
		
		cuenta.setPeriodos(listaPeriodos);
						
		Empresa empresa = new Empresa();
		empresa.setNombre("Google");
		empresa.addCuenta(cuenta);
		
		cuenta.addEmpresa(empresa);

		EntityManagerHelper.beginTransaction();
		
		try
		{
			entityManager.persist(empresa);
		    System.out.println("aaaa "+ cuenta.getPeriodo().size());
			entityManager.flush();
			EntityManagerHelper.commit();	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			EntityManagerHelper.rollback();
		}
		
		
	}

}