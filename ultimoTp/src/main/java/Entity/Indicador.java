package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Modelo.DAOIndicadorJson;

//para Aplicar el template
@Entity
@Table(name = "Indicador")
public class Indicador implements Entidad {
	
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String formula;
	private boolean sePuedeBorrar;
	private ArrayList<String> valores = new ArrayList<String>();
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> usuarios;*/
	
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) 
	{
		this.formula = formula;
	}
		
	public boolean isSePuedeBorrar() {
		return sePuedeBorrar;
	}
	public void setSePuedeBorrar(boolean sePuedeBorrar) {
		this.sePuedeBorrar = sePuedeBorrar;
	}
	
	
	public ArrayList<String> getAllNombres()
	{
		DAOIndicadorJson dao=new DAOIndicadorJson();
		ArrayList<String> nombres = new ArrayList<String>();
		ArrayList<Indicador> indicadoresPorEmpresa= new ArrayList<Indicador>();
		indicadoresPorEmpresa=dao.getAll();
		
		for(int i=0;i<indicadoresPorEmpresa.size();i++)
		{
			nombres.add(indicadoresPorEmpresa.get(i).getNombre());
		}

		return nombres;
	} 

	
	public boolean existeIndicador(String nombre)
	{
		 DAOIndicadorJson dao=new DAOIndicadorJson();
			
		return (dao.find(nombre).size()>0);
		 
	}
	
	public int aplicarA(Empresa empresa)
	{
		//1. Calculate ( valores ) --> retorna un valor int 
		
		
		//Con una empresa, y la verificacion de Si se puede aplicar,
		//buscar calculadora
		
		//Optional<Cuenta> a = empresa.getCuentas().stream().findFirst(cuenta1 -> cuenta1.getNombre().equals);
		return 0;
	}
	
	public void AltaIndicador(Empresa empresa,Periodo desde,Periodo hasta) throws IOException
	{
		 DAOIndicadorJson dao=new DAOIndicadorJson();
			
		if(!this.existeIndicador(this.getNombre()))
		{
			Indicador indic= new Indicador();
			indic.setNombre(nombre);
			indic.setFormula(formula);
			dao.add(indic);
		}
		
	}
	public ArrayList<String> cuentasDeLaFormula(String formula)
	{
		
		int i=0;
		String word;
		StringTokenizer elementos,subelementos;
		ArrayList<String> palabra= new ArrayList<String>();
		elementos = new StringTokenizer(formula,"(+/*-)");
		while(elementos.hasMoreTokens()){
		  word = elementos.nextToken();
		  i=1;
		  subelementos = new StringTokenizer(word,",");
		  while(subelementos.hasMoreTokens()){
		    palabra.add( subelementos.nextToken());
		    
		    i++;
		  }
		}
		return palabra;
	}
	
	public boolean sePuedeAplicar(String formula)
	{
		//1.tengo que ver si es un indicador o una cuenta
		//2. si es indicador ver si existe en el json y obtener sus cuentas
		//3. si es una cuenta tengo que ver que todas pertenezcan a la misma empresa
		//4. De ambas, verificar su sintaxis  OK!
		//5.de ser OK retornar true
//		if(!this.existeIndicador() )// this.sintaxisCorrecta(formula))
	//	{
			this.cuentasDeLaFormula(formula);
			return false;
		//}
	
	}
	public String getFormulaDeIndicador(String indicador) {
	    return "EBITDA+EBITDA";
	    //cn el nombre del indicador dame la formula
	}
	//cuando usas esta funcion , poner a contadorDeLoop en 0 
	public String sacarIndicadores (String formula,String desde,String hasta,String empresa,int contadorDeLoop) {
		Cuenta cuenta= new Cuenta();
		String formulaDevuelta = formula;
		valores = cuenta.cuentasDeLaFormula(formula); // parseas la formula
		 //int i = contadorDeLoop;
	    int ultimoContador = 0;
	    for(int i=contadorDeLoop;i<valores.size();i++){
	    	String cuentaInd = valores.get(i);
	    	if (!cuenta.perteneceALasCuentas(cuentaInd, desde, hasta, empresa)){
	            String formulaNueva = getFormulaDeIndicador(cuentaInd);
	            String[] parts = formula.split(cuentaInd);
	            StringBuilder formulaFinal = new StringBuilder();
	            formulaFinal.append(parts[0]);
	            //if (i == 0){
	            	//formulaFinal.append("+");
	            //}
	            formulaFinal.append(formulaNueva);
	            if (parts.length > 1){
	            	formulaFinal.append(parts[1]);
	            }
	            formulaDevuelta = formulaFinal.toString();
	            valores = cuenta.cuentasDeLaFormula(formulaDevuelta);
	            i--;
	            //formulaDevuelta = sacarIndicadores(formulaFinal.toString(),desde,hasta,empresa,i);
	            //break; 
	        }
	    }
	    return formulaDevuelta;
	}




}
