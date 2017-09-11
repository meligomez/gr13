package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.CondicionTaxativa;
import Controller.Empresa;
import Controller.Metodologia;
import Controller.MetodologiaDeOrdenamiento;
import Modelo.DAOjson;
import Modelo.DAOmetodologia;
import Modelo.DAOmetodologiaJson;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class UIListaOrdenada extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIListaOrdenada dialog = new UIListaOrdenada();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UIListaOrdenada() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false); // lo ocultas
						dispose(); // lo destruis
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JLabel lblListaDeEmpresas = new JLabel("Lista de empresas ordenada por :");
			lblListaDeEmpresas.setToolTipText("");
			lblListaDeEmpresas.setHorizontalAlignment(SwingConstants.LEFT);
			lblListaDeEmpresas.setForeground(new Color(255, 105, 180));
			lblListaDeEmpresas.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
			lblListaDeEmpresas.setBounds(20, 11, 414, 26);
			getContentPane().add(lblListaDeEmpresas);
		}
		
		JList list = new JList();
		list.setBounds(257, 17, 120, 26);
		getContentPane().add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(20, 65, 381, 152);
		getContentPane().add(list_1);
		DefaultListModel modelo = new DefaultListModel();
		list_1.setModel(modelo);
		Metodologia metodologia= new Metodologia();
		
		ArrayList<CondicionTaxativa> condiciones= new ArrayList<CondicionTaxativa>();
		CondicionTaxativa condicion1= new CondicionTaxativa();
		condicion1.setIndicadorOCuenta("EBITDA");
		condicion1.setExpresion("Mayor");
		condicion1.setValorAComparar(50);

		CondicionTaxativa condicion2= new CondicionTaxativa();
		condicion2.setIndicadorOCuenta("FDS");
		condicion2.setExpresion("Igual");
		condicion2.setValorAComparar(30);
		
		condiciones.add(condicion1);
		condiciones.add(condicion2);
		
		DAOjson dao= new DAOjson();
		ArrayList<Empresa> listaEmpresas = dao.getAll();
		
		DAOmetodologiaJson daometodologia = new DAOmetodologiaJson();	
		//MetodologiaDeOrdenamiento metodologiaOrdenamiento=new MetodologiaDeOrdenamiento(daometodologia.getAll().get(0).getCondiciones());
		MetodologiaDeOrdenamiento metodologiaOrdenamiento=new MetodologiaDeOrdenamiento(condiciones);
		
		listaEmpresas = (ArrayList<Empresa>) metodologiaOrdenamiento.ordenarLista(listaEmpresas);
		for(Empresa emp : listaEmpresas){
			modelo.addElement(emp.getNombre());
		}
		//modelo.addElement(metodologiaOrdenamiento.ordenarLista(listaEmpresas).toString());
	}
}
