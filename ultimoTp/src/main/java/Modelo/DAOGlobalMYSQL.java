package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import Entity.Cuenta;
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
	public boolean existeUsuarioYContraseña(String nombre,String contraseña) {
		EntityManager em = EntityManagerHelper.entityManager();
	return em.createNativeQuery("select nombre from redinversiones.usuario where contraseña='" +contraseña+"'").getResultList().contains(nombre);
		
	}
	public ArrayList<Indicador> getIndicadores(String empresaB,String periodoDesde,String periodoHasta) {
		EntityManager em = EntityManagerHelper.entityManager();
		return  (ArrayList<Indicador>) em.createNativeQuery("select * from indicador",Indicador.class).getResultList();

	}
	public boolean sePuedeBorrarIndicador(String nombreIndicador) {
		EntityManager em = EntityManagerHelper.entityManager();
		return em.createNativeQuery("select sePuedeBorrar from indicador where nombre= '"+nombreIndicador+"'").getSingleResult().equals(false);
	}
	public void borrarIndicador(String nombreIndicador) {
		EntityManager em = EntityManagerHelper.entityManager();
		EntityManagerHelper.beginTransaction();
		 em.createNativeQuery("delete from indicador where nombre= '"+nombreIndicador+"'").executeUpdate();
		 em.flush();
		EntityManagerHelper.commit();		 
		 //Indicador foo= new Indicador();
//		
//		 foo = em.find(Indicador.class, foo.getIdByName(nombreIndicador));
//		 em.remove(foo);

	}
	public ArrayList<Empresa> getAllEmp(){
		EntityManager em = EntityManagerHelper.entityManager();
		ArrayList<Empresa> emp= new ArrayList<Empresa>();
		//emp=em.createNativeQuery("select * from redinversiones.empresa").getResultList();
		
		return (ArrayList<Empresa>) em.createNativeQuery("select * from empresa",Empresa.class).getResultList();

	}
	public ArrayList<Cuenta> getCuentas(String empresaB,String periodoDesde,String periodoHasta) {
		EntityManager em = EntityManagerHelper.entityManager();
		return  (ArrayList<Cuenta>) em.createNativeQuery("select c.id, c.nombre,c.valor from empresa e join cuenta_empresa ce on (ce.empresas_id=e.id) join periodo p on (p.cuenta_empresa=ce.id) join cuenta c on(c.id=ce.cuentas_cuenta_id) where e.nombre ='"+empresaB+"' and  p.desde='"+periodoDesde+"' and  p.hasta= '"+periodoHasta+"'",Cuenta.class).getResultList();

	}
	public Empresa findEmpresa(String empresaB)
	{
		EntityManager em = EntityManagerHelper.entityManager();
		return (Empresa) em.createNativeQuery("select * from empresa e where e.nombre='"+empresaB+"'"
                                     ,Empresa.class).getSingleResult();

	}
	public ArrayList<Periodo> getAllPeriodos() {
		EntityManager em = EntityManagerHelper.entityManager();
		ArrayList<Periodo> per= new ArrayList<Periodo>();
		return (ArrayList<Periodo>) em.createNativeQuery("select  id,desde,hasta,valorCuenta,cuenta_empresa,cuenta_id from periodo",Periodo.class).getResultList();

	}
	public ArrayList<Indicador> getAllIndicadores() {
		EntityManager em = EntityManagerHelper.entityManager();
		return  (ArrayList<Indicador>) em.createNativeQuery("select * from indicador",Indicador.class).getResultList();

		
	}

}
