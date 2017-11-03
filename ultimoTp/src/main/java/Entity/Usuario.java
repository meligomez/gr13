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
	public String nombre;
	public String contrase�a;
	
	@OneToMany( mappedBy="usuario")
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="usuario")
	public List<Indicador> indicadores;
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
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
}
