package Entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Entidad{

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="nombre",unique = true,nullable = false)
	public String nombre;
	public String contraseña;
	
	
	//@OneToMany(mappedBy = "usuario")
	//public List<Metodologia> metodologias;
	
	//@OneToMany( mappedBy="usuario")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="usuario")
	public List<Indicador> indicadores;
		
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "usuario")
	public List<CondicionTaxativa> listaCondiciones;
	
	public Usuario() {
		super();
		this.indicadores = new LinkedList<>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	public void addIndicador(Indicador indicador){
		this.indicadores.add(indicador);
	}

	public List<CondicionTaxativa> getListaCondiciones() {
		return listaCondiciones;
	}

	public void setListaCondiciones(List<CondicionTaxativa> listaCondiciones) {
		this.listaCondiciones = listaCondiciones;
	}
	
	
}
