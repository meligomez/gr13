package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import Entity.Entidad;
import Entity.Indicador;
import Entity.Usuario;
import db.EntityManagerHelper;

public class DAOGlobalMYSQL implements DAOGlobal{

	@Override
	public void add(Entidad entidad) throws IOException {
		
		EntityManager entityManager= EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		try
		{
			entityManager.persist(entidad);
			entityManager.flush();
			EntityManagerHelper.commit();	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			EntityManagerHelper.rollback();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nombre) {
		/*Indicador indicador= new Indicador();
		indicador= (Indicador) this.find(nombre);
		indicador.setSePuedeBorrar(false);
		EntityManager entityManager= EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		try
		{
			entityManager.refresh(indicador);
			entityManager.flush();
			EntityManagerHelper.commit();	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			EntityManagerHelper.rollback();
		}*/
		
	}

	@Override
	public void update(Indicador indicador, String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Indicador> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Indicador> find(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAllStruct() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public Usuario findPorId(int id) {
		EntityManager em = EntityManagerHelper.entityManager();
		Usuario usuario = (Usuario) em.find(Usuario.class, id);
		return usuario;
	}

}
