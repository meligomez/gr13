import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import Entity.CondicionOrdenamiento;
import Entity.CondicionTaxativa;
import Entity.Empresa;
import Entity.Indicador;
import Entity.Metodologia;
import Entity.Usuario;
import Modelo.DAOGlobalMYSQL;
import db.EntityManagerHelper;
import Comparator.Comparador;

public class Program {

	public static void main(String[] args) {
//		Indicador ind = new Indicador();
//		String nombre = "Liquidez Corriente";
//		System.out.println("vfdvdf "+ind.getFormulaDeIndicador(nombre));
//		String nombr = "Super Liquida";
//		DAOGlobalMYSQL<Metodologia> metodologia = new DAOGlobalMYSQL<Metodologia>(Metodologia.class);
//		Metodologia m = metodologia.findEntidadWithNombre(nombr);
//		System.out.println("  " + m.getNombre());
		List<String> empresasSeleccionadas= new ArrayList<>();
		empresasSeleccionadas.add("Cloud");empresasSeleccionadas.add("Ford");empresasSeleccionadas.add("Google"); 
		DAOGlobalMYSQL<Empresa> dao = new DAOGlobalMYSQL<Empresa>(Empresa.class);
		List<Empresa> lista = dao.getAllEmp();
		
		//**Agregar empresas mediante stringsss****
		List<Empresa> empresas = new ArrayList<>();
		
		for(Empresa e : lista){
			Empresa a = new Empresa();
			if(empresasSeleccionadas.stream().anyMatch((String nombre)-> nombre.equals(e.getNombre()))){
				empresas.add(e);
			}
		}
		
		//CondicionOrdenamiento condicion = new CondicionOrdenamiento(empresas,Comparador.MENORAMAYOR,"Liquidez Corriente");
		CondicionOrdenamiento condicion = findCondicion("Paula","prueba");
		condicion.setListaEmpresas(empresas);
		condicion.darValorAEmpresas("2016","2016");
		
		for(Empresa e: empresas){
			System.out.println("Nopmbre "+ e.getNombre()+" vaslor "+ e.getValor());
		}
		
		condicion.ordenar();
		System.out.println("Ordenado");
		
		for(Empresa e: empresas){			
			System.out.println("Nopmbre "+ e.getNombre()+" vaslor "+ e.getValor());
		}
		
//		//****AGREGAR USUARIO CON CONDICIONORDENIAMIENTO
//		DAOGlobalMYSQL dao = new DAOGlobalMYSQL();
//		Usuario usuario =  dao.findPorId(1);
//		Metodologia metodologia = new Metodologia();
//		metodologia.setNombre("prueba");
//		metodologia.setUsuario(usuario);
//		CondicionOrdenamiento condicion = new CondicionOrdenamiento(null,Comparador.MENORAMAYOR,"Liquiedezz");
//		List<CondicionOrdenamiento> lista = new ArrayList<>();
//		lista.add(condicion);
//		condicion.setMetodologia(metodologia);
//		metodologia.setCondicionesOrdenamiento(lista);
//		EntityManager entityManager= EntityManagerHelper.getEntityManager();
//		EntityManagerHelper.beginTransaction();
//		try
//		{
//			entityManager.persist(metodologia);
//			entityManager.flush();
//			EntityManagerHelper.commit();	
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//			EntityManagerHelper.rollback();
//		}
		
//		DAOGlobalMYSQL<Metodologia> dao = new DAOGlobalMYSQL<Metodologia>(Metodologia.class);
//		List<Metodologia> lista = dao.getAll().stream().filter(m -> m.getUsuario().getNombre().equals("Paula")).collect(Collectors.toList());
//		//System.out.println("TAMA�OOO "+ lista.get(2).getNombre());
//		CondicionOrdenamiento cond = findCondicion("Paula","prueba");
//		System.out.println("dddd "+ cond.getIndicadorCuenta());
	}
	
	public static CondicionOrdenamiento findCondicion(String usuario, String condicion)
	{
		EntityManager em = EntityManagerHelper.entityManager();
		return (CondicionOrdenamiento) em.createNativeQuery("SELECT c.id, c.comparador, c.indicadorCuenta,c.metodologia_id FROM inversiones.condicionordenamiento c "
				+ "join inversiones.metodologia m on (m.id=c.metodologia_id) "
				+ "join inversiones.usuario u "
				+ "where m.nombre = '"+condicion+"' and u.nombre ='"+usuario+"'"
                                     ,CondicionOrdenamiento.class).getSingleResult();

	}

}
