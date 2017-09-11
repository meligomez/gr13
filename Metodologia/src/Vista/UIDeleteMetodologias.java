package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;

public class UIDeleteMetodologias extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIDeleteMetodologias dialog = new UIDeleteMetodologias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UIDeleteMetodologias() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBounds(0, 0, 446, 40);
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout());
			{
				JLabel lblSeleccioneLaMetodologia = new JLabel("Seleccione la metodolog\u00EDa que desea eliminar :");
				lblSeleccioneLaMetodologia.setForeground(Color.PINK);
				lblSeleccioneLaMetodologia.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel.add(lblSeleccioneLaMetodologia);
			}
		}
		{
			JLabel lblMetodologa = new JLabel("Metodolog\u00EDa:");
			lblMetodologa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMetodologa.setBounds(24, 77, 124, 20);
			contentPanel.add(lblMetodologa);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			comboBox.setBounds(192, 79, 116, 20);
			contentPanel.add(comboBox);
		}
		{
			JButton btnBorrar = new JButton("Borrar");
			btnBorrar.setForeground(Color.BLACK);
			btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnBorrar.setBounds(142, 183, 106, 23);
			contentPanel.add(btnBorrar);
		}
		{
			JLabel label = new JLabel(" ");
			label.setForeground(new Color(139, 0, 0));
			label.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label.setBounds(10, 108, 414, 62);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	}

}
