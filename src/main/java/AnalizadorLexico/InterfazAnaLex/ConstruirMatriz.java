package AnalizadorLexico.InterfazAnaLex;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import AnalizadorLexico.AnaLex.AutomataFinito;
import AnalizadorLexico.AnaLex.AutomataFinitoMatriz;

/**
* Diálogo para la clase Analizador. Proporciona una forma de definir la matriz de un autómata finito.
* Parte adicional de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco Pérez y Pablo Ascorbe Fernández
* @version V1 - 22/05/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AutomataFinitoMatriz
* @see Analizador
*/
public class ConstruirMatriz extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	
	int Estados;
	String[] Simbolos;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			DefinirAutomataDialogo dialog = new DefinirAutomataDialogo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param AF Autómata finito. (Nota: Este atributo no sirve para nada.)
	 * @param ali Interfaz del analizador léxico que genera el diálogo.
	 */
	public ConstruirMatriz(AutomataFinito AF, Analizador ali, int NúmeroEstados, String[] Simbolos ) {
		setResizable(false);
		
		Estados = NúmeroEstados;
		this.Simbolos = Simbolos;
		
		setTitle("Matriz de transiciones del analizador l\u00E9xico");
		setBounds(100, 100, 249, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.setFont(new Font("Consolas", Font.PLAIN, 15));
			table.setBounds(10, 11, 303, 16);
			DefaultTableModel dtm = new DefaultTableModel(0, 0);
			String header[] = new String[Simbolos.length+1];
			header[0] =  "Estado";
			for ( int i = 1; i <= Simbolos.length; ++i) {
				header[i] = Simbolos[(i-1)];
			}
			dtm.setColumnIdentifiers(header);
			table.setModel(dtm);
			dtm.addRow(header);
			for ( int i = 0; i < NúmeroEstados; ++i) {
				Object[] Aux = new String[Simbolos.length+1];
				Aux[0] = (i+1)+""; 
				for ( int j = 1; j < Simbolos.length; ++j) {
					Aux[j] = null;
				}
				dtm.addRow(Aux);
			}
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
						
						construirAF(AF, ali);
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.pack();
	}
	
	/**
    * Registra los datos introducidos por pantalla en el analizador léxico (interfaz).
    * @param AF Autómata finito. (Nota: Este atributo no sirve para nada.)
    * @param ali Interfaz del analizador léxico. Guardará el autómata finito introducido.
    * @see AnalizadorLexicoInterfaz#guardarAF(AutomataFinito)
    */
	private void construirAF(AutomataFinito AF, Analizador ali) {
		
		int[][] matriz = new int[this.Estados][this.Simbolos.length];
		
		for(int row = 1; row < this.Estados+1; row++) {
			
			for(int col = 1; col < this.Simbolos.length+1; col++) {
				
				matriz[row-1][col-1] = this.table.getModel().getValueAt(row, col) == null ? -1 : Integer.parseInt((String) this.table.getModel().getValueAt(row, col));
				
			}
			
		}
		
		ali.setMatriz(matriz);
		
	}


}
