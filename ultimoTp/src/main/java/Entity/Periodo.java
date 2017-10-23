package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;

@Entity
@Table(name="periodo")
public class Periodo 
{
	String desde;
	String hasta;
	int valorCuenta;
	
	public int getValorCuenta() {
		return valorCuenta;
	}
	public void setValorCuenta(int valor) {
		this.valorCuenta = valor;
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
