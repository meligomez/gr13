

import javax.persistence.EntityManager;

import Entity.*;
import db.EntityManagerHelper;
public class Main {

	public static void main(String[] args) {
		EntityManager entityManager= EntityManagerHelper.getEntityManager();

		Usuario user = new Usuario();
		user.setNombre("Alejandro");
		user.setContraseña("000");
		
		Indicador indicador1 = new Indicador();
		indicador1.setNombre("Indicador1");
		indicador1.setFormula("EBITDA+IngersoNeto");
		indicador1.setSePuedeBorrar(false);
		user.addIndicador(indicador1);
		
		EntityManagerHelper.beginTransaction();
		try
		{
			entityManager.persist(user);
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