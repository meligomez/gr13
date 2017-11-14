package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import Entity.Empresa;
import Entity.Entidad;
import Entity.Indicador;
import Entity.Periodo;
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
	public boolean existeUsuarioYContrase�a(String nombre,String contrase�a) {
		EntityManager em = EntityManagerHelper.entityManager();
	return em.createNativeQuery("select nombre from redinversiones.usuario where contrase�a='" +contrase�a+"'").getResultList().contains(nombre);
		
	}
	
	public ArrayList<Empresa> getAllEmp(){
		EntityManager em = EntityManagerHelper.entityManager();
		ArrayList<Empresa> emp= new ArrayList<Empresa>();
		//emp=em.createNativeQuery("select * from redinversiones.empresa").getResultList();
		
		return (ArrayList<Empresa>) em.createNativeQuery("select * from empresa",Empresa.class).getResultList();

	}

	public ArrayList<Periodo> getAllPeriodos() {
		EntityManager em = EntityManagerHelper.entityManager();
		ArrayList<Periodo> per= new ArrayList<Periodo>();
		return (ArrayList<Periodo>) em.createNativeQuery("select  id,desde,hasta,valorCuenta,cuenta_empresa,cuenta_id from periodo",Periodo.class).getResultList();

	}

}
