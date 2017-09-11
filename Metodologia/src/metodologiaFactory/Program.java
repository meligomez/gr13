package metodologiaFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Controller.CondicionTaxativa;
import Controller.*;
import Controller.Empresa;
import Controller.Metodologia;
import Controller.MetodologiaDeOrdenamiento;
import Modelo.DAOjson;

public class Program {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		expresionFactory cond = new creadorConcretoMayor();
//		expresion condicionConcreta = cond.FactoryMethod();
//		
		Metodologia metodologia=new Metodologia();
		metodologia.setNombre("Metodologia1");
		
		ArrayList<CondicionTaxativa> condiciones= new ArrayList<CondicionTaxativa>();
		CondicionTaxativa condicion1= new CondicionTaxativa();
		condicion1.setIndicadorOCuenta("EBITDA");
		condicion1.setExpresion("Mayor");
		condicion1.setValorAComparar(50);

		CondicionTaxativa condicion2= new CondicionTaxativa();
		condicion2.setIndicadorOCuenta("FDS");
		condicion2.setExpresion("Igual");
		condicion2.setValorAComparar(30);
		
		condiciones.add(condicion1);
		condiciones.add(condicion2);
		
		metodologia.setCondiciones(condiciones);
		
		
		Metodologia metodo = new Metodologia();
		
		ManejadorDeExpresiones manejador= new ManejadorDeExpresiones();
		
		manejador.setCreadorFactory(manejador.concatenarPackage(condicion2.getExpresion()));
		
		manejador.crearExpresion();
		//tengo q setearle el valor q obtenga de esa cunta
		//o inducador de una determinada empresa
		//en un periodo 
		int valorCuentaOIndicador = 30;
		//System.out.println(manejador.getExpresion().cumpleCondicion( valorCuentaOIndicador,condicion2.getValorAComparar() ));
	
//		System.out.println("Metodologia De Ordenamiento");
//		
		
		DAOjson dao= new DAOjson();
		ArrayList<Empresa> listaEmpresas = dao.getAll();
		
//		for(Empresa empresa : listaEmpresas){
//			System.out.println("Val: " + empresa.getNombre());
//			
//		}
		
//		MetodologiaDeOrdenamiento prueba = new MetodologiaDeOrdenamiento(condiciones);
//		
//		prueba.ordenarLista(listaEmpresas);
//		
//		Empresa emp = listaEmpresas.get(0);
//		for(Empresa empresa : listaEmpresas){
//			System.out.println("Val: " + empresa.getNombre());
//			
//		}		
		Cuenta cuenta = new Cuenta();
		//cuenta.getAllNombreCuentas();
		ArrayList<String> nombres= new ArrayList<String>();
		listaEmpresas.stream().map(empresa->empresa.getCuentas())
		 .flatMap(cuentas -> cuentas.stream())
		 .map(unaCuenta->unaCuenta.getNombre()).forEach(unaC->nombres.add(unaC));
		//System.out.println(nombres.contains());
		/*for(Cuenta cuenta: emp.getCuentas()){
			for(Periodo per:cuenta.getPeriodo()){
				if(condicion1.cumpleCondicion(emp.getNombre(), per.getDesde(), per.getHasta())){
					System.out.println("true");
				}
				else
					System.out.println("no cumple con condicion");
			}
		}*/
		
		
	}

}


