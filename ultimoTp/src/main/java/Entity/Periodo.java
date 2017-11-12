package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;

@Entity
@Table(name="Periodo")
public class Periodo 
{
	@Id
	@GeneratedValue
	private int id;
	
	private String desde;
	private String hasta;
	private int valorCuenta;
	
	@ManyToOne
	@JoinColumn(name="cuenta_id", nullable=false)
	private Cuenta cuenta;
	
	public int getValorCuenta() {
		return valorCuenta;
	}
	public void setValorCuenta(int valor) {
		this.valorCuenta = valor;
	}
	
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public int getId() {
		return id;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
//		Calendar calendario = GregorianCalendar.getInstance();
//		desde= calendario.getTime();
//		//System.out.println(desde);
//		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println(formatoDeFecha.format(desde));
		this.desde=desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta=hasta;
		
	}
	
//	public boolean pertenecePeriodo(int anio){
//		return true;
//	}
//	
}
