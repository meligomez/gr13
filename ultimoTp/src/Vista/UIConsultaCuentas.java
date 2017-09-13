package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Empresa;
import Controller.Cuenta;
import Modelo.DAOjson;
import Modelo.RepositorioDeEmpresas;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

public class UIConsultaCuentas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	public static RepositorioDeEmpresas empresa;
	public static ArrayList<Empresa> listaDeEmpresas;
	
	
	public static RepositorioDeEmpresas getEmpresa() 
	{
		return empresa;
	}

	public static void setEmpresa(RepositorioDeEmpresas empresa) 
	{
		UIConsultaCuentas.empresa = empresa;
	}

	public ArrayList<Empresa> getListaDeEmpresas() 
	{
		return listaDeEmpresas;
	}

	public void setListaDeEmpresas(ArrayList<Empresa> listaDeEmpresas) 
	{
		this.listaDeEmpresas = listaDeEmpresas;
	}
    
	public void getAllNombres(JComboBox comboEmpresa)
	{
		for(int i = 0; i < listaDeEmpresas.size(); i++) {
		    comboEmpresa.addItem(listaDeEmpresas.get(i).getNombre());
		}
	}
	
	public static void main(String[] args) {
		try 
		{
			UIConsultaCuentas dialog = new UIConsultaCuentas(listaDeEmpresas,empresa);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	//Creo esta lista para cargar los datos del json

	public UIConsultaCuentas(ArrayList<Empresa> listaDeEmpresas, RepositorioDeEmpresas empresa) {
		
		//Seteo la lista de empresas recibida del otro controlPanel a esta lista local
		this.setListaDeEmpresas(listaDeEmpresas);
		this.setEmpresa(empresa);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false); // lo ocultas
						dispose(); // lo destruis

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
			}
		}
		contentPanel.setLayout(null);
		
		JComboBox comboEmpresa = new JComboBox();
		this.getAllNombres(comboEmpresa);
		comboEmpresa.setBounds(104, 66, 81, 20);
		contentPanel.add(comboEmpresa);
		
		JComboBox comboDesde = new JComboBox();
		comboDesde.setModel(new DefaultComboBoxModel(new String[] {"01/01/2010", "01/01/2011", "01/06/2015"}));
		comboDesde.setBounds(104, 97, 96, 20);
		contentPanel.add(comboDesde);
		
		JLabel lblNewLabel = new JLabel("Seleccione la Empresa y el Rango de Periodos a Consultar!  \u2193");
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 414, 43);
		contentPanel.add(lblNewLabel);
		
		JComboBox comboHasta = new JComboBox();
		comboHasta.setModel(new DefaultComboBoxModel(new String[] {"30/06/2010", "31/12/2011", "30/12/2015"}));
		comboHasta.setBounds(104, 128, 96, 20);
		contentPanel.add(comboHasta);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmpresa.setBounds(10, 65, 68, 20);
		contentPanel.add(lblEmpresa);
		
		JLabel lblPeriodoDesde = new JLabel("Periodo Desde:");
		lblPeriodoDesde.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeriodoDesde.setBounds(10, 93, 99, 27);
		contentPanel.add(lblPeriodoDesde);
		
		JLabel lblPeriodoHasta = new JLabel("Periodo Hasta:");
		lblPeriodoHasta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeriodoHasta.setBounds(10, 122, 99, 31);
		contentPanel.add(lblPeriodoHasta);
		
		JList lista = new JList();
		lista.setBounds(218, 61, 203, 167);
		DefaultListModel modelo = new DefaultListModel();
		lista.setModel(modelo);
		contentPanel.add(lista);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(58, 164, 89, 23);
		contentPanel.add(btnConsultar);
		
		btnConsultar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			
				//Corroboro que no elija la opcion "Periodo" ni "Empresa"
				if(comboEmpresa.getSelectedItem().toString()=="Empresa")
				{
					lblNewLabel.setText("Seleccione Periodo y Empresa! ");		
				}
				else
				{
					if(listaDeEmpresas.size()==0)
					{
						lblNewLabel.setText("No existen ctas o no Cargo Anio! ");	
					}
					else
					{
						lblNewLabel.setText("Cuentas de: "+comboEmpresa.getSelectedItem().toString() + " del "+comboDesde.getSelectedItem().toString() + " Hasta "+comboHasta.getSelectedItem().toString());
						Cuenta cuenta= new Cuenta();
						//Limpio por cada vuelta la lista
						modelo.clear();
						//Por cada lista de cuentas muestro su nombre y valor
						for (int i = 0; i <cuenta.findCtaPorEmpresa(listaDeEmpresas, comboDesde.getSelectedItem().toString(),comboHasta.getSelectedItem().toString(),comboEmpresa.getSelectedItem().toString()).size(); i++)
						   {
							modelo.addElement(cuenta.findCtaPorEmpresa(listaDeEmpresas, comboDesde.getSelectedItem().toString(),comboHasta.getSelectedItem().toString(),comboEmpresa.getSelectedItem().toString()).get(i).getNombre());

							modelo.addElement(cuenta.findCtaPorEmpresa(listaDeEmpresas,comboDesde.getSelectedItem().toString(),comboHasta.getSelectedItem().toString(), comboEmpresa.getSelectedItem().toString()).get(i).getPeriodo().get(0).getValorCuenta());
						   

						   }
					}
				
				}
			}
		});

	}
}
