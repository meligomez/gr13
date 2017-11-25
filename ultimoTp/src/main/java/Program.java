import Entity.Indicador;
import Entity.Metodologia;
import Modelo.DAOGlobalMYSQL;

public class Program {

	public static void main(String[] args) {
		Indicador ind = new Indicador();
		String nombre = "Liquidez Corriente";
		System.out.println("vfdvdf "+ind.getFormulaDeIndicador(nombre));
		String nombr = "Super Liquida";
		DAOGlobalMYSQL<Metodologia> metodologia = new DAOGlobalMYSQL<Metodologia>(Metodologia.class);
		Metodologia m = metodologia.findEntidadWithNombre(nombr);
		System.out.println("  " + m.getNombre());
	}

}
