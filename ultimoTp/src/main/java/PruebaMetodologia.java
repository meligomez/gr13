import java.util.List;

import javax.persistence.EntityManager;

import Entity.CondicionTaxativa;
import Entity.Metodologia;
import Modelo.DAOGlobalMYSQL;
import Modelo.RepositorioDeMetodologia;
import db.EntityManagerHelper;

public class PruebaMetodologia {

	public static void main(String[] args) {
		DAOGlobalMYSQL<Metodologia> dao = new DAOGlobalMYSQL<Metodologia>(Metodologia.class);
		RepositorioDeMetodologia repo = new RepositorioDeMetodologia(dao);
		Class<Metodologia> mm ;
		List<Metodologia> lista = repo.getLista();
		for(Metodologia m: lista){
			System.out.println("FUNCIONANANAN: "+ m.getNombre());
		}
		Metodologia m = lista.get(0);
		List<CondicionTaxativa> listaC = m.getCondiciones();
//		for(CondicionTaxativa c : listaC){
//			System.out.println("eee" + c.getExpresion());
//		}
		
//		DAOGlobalMYSQL<Empresa> daoE = new DAOGlobalMYSQL<Empresa>(Empresa.class);
//		RepositorioDeEmpresas repoE = new RepositorioDeEmpresas(daoE);
//		System.out.println("valores "+repoE.getLista().get(0).getNombre());
		
		
		///*************APLICAR LISTA DE CONDICIONTAX A EMPRESAPERIODO************************ 
		String empresa="Facebook";String desde ="2016";String hasta="2016";
//		for (CondicionTaxativa c: listaC){
//			//System.out.println("VALORRR "+buscarValorINmysql(c.getIndicadorOCuenta(),desde,hasta,empresa));
//			boolean valor = c.cumpleCondicion(empresa, desde, hasta,buscarValorINmysql(c.getIndicadorOCuenta(),desde,hasta,empresa));
//		}
		//System.out.println("ss "+listaC.get(0).getExpresion() + "VLAORRR "+buscarValorINmysql(listaC.get(0).getIndicadorOCuenta(),desde,hasta,empresa));
		System.out.println("FUNCIONA ? " + listaC.stream().allMatch(c -> c.cumpleCondicion(empresa, desde, hasta,buscarValorINmysql(c.getIndicadorOCuenta(),desde,hasta,empresa))));


	}
	
	public static int buscarValorINmysql(String cuenta, String desde, String hasta, String empresa){
		EntityManager em = EntityManagerHelper.entityManager();
				return (int) em.createNativeQuery("Select valorCuenta "
				+ "from tp.periodo p join tp.empresa_cuenta ce on(ce.id=p.empresa_cuenta_id) "
				+ "join tp.empresa e on(e.id=ce.empresa_id) join tp.cuenta c "
				+ "on(c.id=ce.cuenta_id) where c.nombre='"+cuenta+"' and p.desde ='"+desde+"' "
						+ "and p.hasta ='"+hasta+"' and e.nombre ='"+empresa+"'").getSingleResult();

	}

}
