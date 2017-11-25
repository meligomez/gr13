package Comparator;

public enum Comparador {
	
	MAYORAMENOR{
		public boolean comparar(double valor, double otroValor){
			return valor > otroValor;
		}
	},
	
	MENORAMAYOR{
		public boolean comparar(double valor,double otroValor){
			return valor < otroValor;
		}
	};
	
	public abstract boolean comparar(double valor, double otroValor);
}
