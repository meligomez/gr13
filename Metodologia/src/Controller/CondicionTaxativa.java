package Controller;

import metodologiaFactory.ManejadorDeExpresiones;
import metodologiaFactory.*;
public class CondicionTaxativa {
	String indicadorOCuenta;
	String expresion;
	int valorAComparar;
	
	
	public String getIndicadorOCuenta()
	{
		return indicadorOCuenta;
	}
	public void setIndicadorOCuenta(String indicadorOCuenta) 
	{
		this.indicadorOCuenta = indicadorOCuenta;
	}
	public String getExpresion() 
	{
		return expresion;
	}
	public void setExpresion(String expresion) 
	{
		this.expresion = expresion;
	}
	public int getValorAComparar() 
	{
		return valorAComparar;
	}
	public void setValorAComparar(int valorAComparar) 
	{
		this.valorAComparar = valorAComparar;
	}
	
	//Por ahora supongo que es una cuenta nomas
	public int obtenerValorDelIndicadorOCuenta(String empresa, String desde, String hasta) 
	{
		int valor=0;
		Cuenta cuenta = new Cuenta();
		if(cuenta.perteneceALasCuentas(indicadorOCuenta, desde,hasta, empresa))
		{
			valor=cuenta.obtenerValor(indicadorOCuenta,desde,hasta,empresa);

			System.out.println("El valor de la cuenta"+valor+ "eem" +indicadorOCuenta);
		}
		return valor;
	}
	public boolean cumpleCondicion(String empresa, String desde, String hasta) 
	{
		System.out.println(this.obtenerExpresion(this.getExpresion()).cumpleCondicion(this.obtenerValorDelIndicadorOCuenta(empresa, desde,hasta), this.valorAComparar));
		System.out.println(this.valorAComparar);
		System.out.println(this.obtenerValorDelIndicadorOCuenta(empresa, desde,hasta));
		return this.obtenerExpresion(this.getExpresion()).cumpleCondicion(this.obtenerValorDelIndicadorOCuenta(empresa, desde,hasta), this.valorAComparar);
	}
	public expresion obtenerExpresion(String expresion) 
	{
		ManejadorDeExpresiones manejador= new ManejadorDeExpresiones();
		manejador.setCreadorFactory(manejador.concatenarPackage(expresion));
		manejador.crearExpresion();
		System.out.println(manejador.getExpresion());
		return manejador.getExpresion();
		
	}

	public boolean cumpleCondicionPrueba(String empresa, String desde, String periodo)
	{
		return false;
	}
}
