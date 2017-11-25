import java.util.ArrayList;
import java.util.List;

import Entity.CondicionOrdenamiento;
import Entity.Empresa;
import Entity.Indicador;
import Entity.Metodologia;
import Modelo.DAOGlobalMYSQL;
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
		
		CondicionOrdenamiento condicion = new CondicionOrdenamiento(empresas,Comparador.MENORAMAYOR,"Liquidez Corriente");
		condicion.darValorAEmpresas("2016","2016");
		
		for(Empresa e: empresas){
			System.out.println("Nopmbre "+ e.getNombre()+" vaslor "+ e.getValor());
		}
		
		condicion.ordenar();
		System.out.println("Ordenado");
		
		for(Empresa e: empresas){			
			System.out.println("Nopmbre "+ e.getNombre()+" vaslor "+ e.getValor());
		}
		
		
	}

}
