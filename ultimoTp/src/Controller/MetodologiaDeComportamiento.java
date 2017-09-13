package Controller;
import java.util.*;

public class MetodologiaDeComportamiento {
	
	private String nombre;
	private ArrayList<CondicionTaxativa> listaCondiciones;
	
	
	public MetodologiaDeComportamiento( ArrayList<CondicionTaxativa> listaCondiciones) {
		super();
		this.nombre = nombre;
		this.listaCondiciones = listaCondiciones;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<CondicionTaxativa> getListaCondiciones() {
		return listaCondiciones;
	}
	public void setListaCondiciones(ArrayList<CondicionTaxativa> listaCondiciones) {
		this.listaCondiciones = listaCondiciones;
	}
	
	public List<Empresa> ordenarLista(List<Empresa> listaEmpresas){
		
		Collections.sort(listaEmpresas, new Comparator<Empresa>(){
			@Override
			public int compare(Empresa empresa1,Empresa empresa2){								
				return darPeso(empresa1) -  darPeso(empresa2);
			}
		});
		return listaEmpresas;
	}
	
	public int darPeso(Empresa empresa){
		int peso = 0;
		
		/*for(CondicionTaxativa condicion :this.listaCondiciones){
			for(int i = 0; i<empresa.getCuentas().size();i++){
				if(condicion.cumpleCondicion(empresa.getNombre(), empresa.getCuentas().get(i).getPeriodo().get(0).getDesde(), empresa.getCuentas().get(i).getPeriodo().get(1).getHasta())){
					peso+=20;
				}
			}
		}*/
		
		/*for(CondicionTaxativa condicion : this.listaCondiciones){
			for(Cuenta cuenta: empresa.getCuentas()){
				if(condicion.cumpleCondicion(empresa.nombre, cuenta.getPeriodo().get(0).getDesde(), cuenta.getPeriodo().get(1).getHasta())){
					peso=+20;
				}
			}
		}*/
		
		//empresa.getCuentas().get(j).getPeriodo().get(0)
		
		for(int i = 0; i<this.listaCondiciones.size();i++){
			for(int j = 0; j < empresa.getCuentas().size();j++){
				if(listaCondiciones.get(i).cumpleCondicion(empresa.getNombre(), empresa.getCuentas().get(j).getPeriodo().get(0).getDesde(), empresa.getCuentas().get(j).getPeriodo().get(1).getHasta())){
					peso+=20;
				}
			}
		}
		
		
		
		return peso;
	}
	
	

}

