package InterfazAnaLex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AnaLex.AnalizadorLexico;

/**
* Diálogo para la clase AnalizadorLexicoInterfaz. Proporciona una forma de definir las equivalencias entre estados finales y tokens.
* Parte adicional de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco Pérez
* @version V2 - 28/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexicoInterfaz
*/
public class DefinirEquivTokensDialogo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			DefinirEquivTokensDialogo dialog = new DefinirEquivTokensDialogo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param equivTokens Diccionario con la correspondencia de estados finales y tokens. (Nota: Este atributo no sirve para nada.)
	 * @param ali Interfaz del analizador léxico que genera el diálogo.
	 */
	public DefinirEquivTokensDialogo(Map<Integer, String> equivTokens, AnalizadorLexicoInterfaz ali) {
		
		setTitle("Equivalencia de estados finales - tokens");
		setBounds(100, 100, 339, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Estado", "Token"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"New column", "New column"
				}
			));
			contentPanel.add(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						construirEquivTokens(equivTokens, ali);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	/**
    * Registra los datos introducidos por pantalla en el analizador léxico (interfaz).
    * @param equivTokens Diccionario con la correspondencia de estados finales y tokens. (Nota: Este atributo no sirve para nada.)
    * @param ali Interfaz del analizador léxico. Guardará el mapa de estados finales - tokens.
    * @see AnalizadorLexicoInterfaz#guardarEquivTokens(Map)
    */
	private void construirEquivTokens(Map<Integer, String> equivTokens, AnalizadorLexicoInterfaz ali) {
		
		equivTokens = new HashMap<>();
		
		for(int row = 1; row < 20; row++) {
			
			if(this.table.getModel().getValueAt(row, 0) != null && this.table.getModel().getValueAt(row, 1) != null) {
				
				equivTokens.put(Integer.parseInt((String) this.table.getModel().getValueAt(row, 0)), (String) this.table.getModel().getValueAt(row, 1));
				
			}
			
		}
		
		ali.guardarEquivTokens(equivTokens);
		
	}

}