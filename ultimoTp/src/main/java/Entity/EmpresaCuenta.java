package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="empresa_cuenta")
public class EmpresaCuenta {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="cuenta_id", nullable=false)
	private Cuenta cuenta;
	
	public EmpresaCuenta() {
		// TODO Auto-generated constructor stub
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
	
	
	

}
