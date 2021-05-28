package AnalizadorLexico.InterfazAnaLex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import AnalizadorLexico.AnaLex.AutomataFinito;
import AnalizadorLexico.AnaLex.AutomataFinitoMatriz;

/**
 * Diálogo para la clase Analizador. Proporciona una forma de definir los
 * estados finales de un autómata finito. Parte adicional de la práctica 4 de la
 * asignatura Procesadores de Lenguajes.
 * 
 * @author Pablo Ascorbe Fernández
 * @version V1 - 22/05/2021
 * @see <a href=
 *      "https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía
 *      de la Asignatura: Procesadores de Lenguajes.</a>
 * @see AutomataFinitoMatriz
 * @see Analizador
 */
public class DefinirFinales extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	int Estados;
	int Alfabeto;

	/**
	 * Create the frame.
	 * @param nuevo 
	 */
	public DefinirFinales(AutomataFinito AF, Analizador ali, int Estados, int Alfabeto, boolean nuevo) {
		setResizable(false);

		this.Estados = Estados;
		this.Alfabeto = Alfabeto;

		setTitle("Matriz de transiciones del analizador l\u00E9xico");
		setBounds(100, 100, 249, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.setFont(new Font("Consolas", Font.PLAIN, 14));
			table.setBounds(10, 11, 303, 16);
			DefaultTableModel dtm = new DefaultTableModel(0, 0);
			String header[] = new String[] {"Estado", "¿Final?"};
			dtm.setColumnIdentifiers(header);
			table.setModel(dtm);
			dtm.addRow(header);
			for (int i = 0; i < Estados; ++i) {
				
				Object[] Aux = new String[Alfabeto + 1];
				Aux[0] = (i) + "";
				
//				if(nuevo) {
					
					JComboBox comboBox = new JComboBox();
					comboBox.addItem("Sí");
					comboBox.addItem("No");
					
					for (int j = 1; j < Alfabeto; ++j) {
						table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
					}
//				}
//				else {
//					
//					JComboBox comboBoxSi = new JComboBox();
//					comboBoxSi.addItem("Sí");
//					comboBoxSi.addItem("No");
//					comboBoxSi.setSelectedItem(0);
//					
//					JComboBox comboBoxNo = new JComboBox();
//					comboBoxNo.addItem("Sí");
//					comboBoxNo.addItem("No");
//					comboBoxNo.setSelectedItem(1);
//					
//					for (int j = 1; j < Alfabeto; ++j) {
//						if(AF.getFinalesBooleanList()[j-1]) {
//							table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxSi));
//							comboBoxSi.setSelectedItem("Sí");
//						}
//						else {
//							table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxNo));
//							comboBoxNo.setSelectedItem("No");
//						}
//					}
//				}
				
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
			
			boolean[] finales = new boolean[this.Estados];
			
			for(int row = 1; row < this.Estados+1; row++) {
				
				finales[row-1] = ((String) this.table.getModel().getValueAt(row, 1)).equals("Sí") ? true : false;
				
			}
			
			ali.setFinales(finales);
			
		}

}
