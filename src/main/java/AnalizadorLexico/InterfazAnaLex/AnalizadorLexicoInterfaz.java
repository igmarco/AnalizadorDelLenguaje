package AnalizadorLexico.InterfazAnaLex;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import AnalizadorLexico.AnaLex.*;
import AnalizadorLexico.AnaLex.AnalizadorLexico;
import AnalizadorLexico.AnaLex.AutomataFinito;
import AnalizadorLexico.AnaLex.AutomataFinitoMatriz;
import AnalizadorLexico.AnaLex.ExcepcionDeTransicion;
import AnalizadorLexico.AnaLex.Tools;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

/**
* Interfaz gráfica para el proyecto AnalizadorLéxico. Proporciona una forma de probar analizadores léxicos generalizada.
* Parte adicional de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco Pérez
* @version V2 - 28/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
* @see DefinirAutomataDialogo
* @see DefinirEquivTokensDialogo
*/
public class AnalizadorLexicoInterfaz {

	private JFrame frmAnalizadorLxico;
	private JTextField textField;
	JTextArea textArea;
	
	//----------------------------
	
	AnalizadorLexico AL;
	
	Map<Integer, String> equivTokens;
	
	String cadenaActual;
	String[] simbolos;
	
	boolean automataFinitoInformado;
	boolean equivTokensInformado;
	
	//----------------------------
	
	JButton btnNewButton, btnSiguiente, construir, btnModificarAnalizador;

	/**
	 * Launch the application.
	 * @param args Argumentos del principal.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalizadorLexicoInterfaz window = new AnalizadorLexicoInterfaz();
					window.frmAnalizadorLxico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnalizadorLexicoInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		AL = null;
	
		equivTokens = new HashMap<>();
		
		cadenaActual = null;
		
		automataFinitoInformado = false;
		equivTokensInformado = false;
		
		frmAnalizadorLxico = new JFrame();
		frmAnalizadorLxico.getContentPane().setBackground(new Color(102, 153, 204));
		frmAnalizadorLxico.setResizable(false);
		frmAnalizadorLxico.getContentPane().setForeground(new Color(0, 0, 0));
		frmAnalizadorLxico.setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iCono.png"));
		frmAnalizadorLxico.setTitle("Analizador L\u00E9xico");
		frmAnalizadorLxico.setBounds(100, 100, 666, 573);
		frmAnalizadorLxico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalizadorLxico.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Siguiente Token");
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnNewButton.setBackground(new Color(255, 239, 213));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickSiguienteToken();
				
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(10, 470, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnNewButton);
		
		btnSiguiente = new JButton("Completar An\u00E1lisis");
		btnSiguiente.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSiguiente.setBackground(new Color(255, 239, 213));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickCompletarAnalisis();
				
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(241, 470, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnSiguiente);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 13));
		textField.setBounds(10, 201, 647, 50);
		frmAnalizadorLxico.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 283, 647, 168);
		frmAnalizadorLxico.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Cadena:");
		lblNewLabel.setBounds(10, 176, 46, 14);
		frmAnalizadorLxico.getContentPane().add(lblNewLabel);
		
		JLabel lblTokens = new JLabel("Tokens:");
		lblTokens.setBounds(10, 258, 46, 14);
		frmAnalizadorLxico.getContentPane().add(lblTokens);
		
		JButton btnIndicar = new JButton("Nuevo Analizador");
		btnIndicar.setFont(new Font("Consolas", Font.BOLD, 15));
		btnIndicar.setBackground(new Color(255, 204, 153));
		btnIndicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickNuevoAnalizador();
				
			}
		});
		btnIndicar.setBounds(10, 111, 315, 54);
		frmAnalizadorLxico.getContentPane().add(btnIndicar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Consolas", Font.BOLD, 13));
		btnSalir.setBackground(new Color(224, 255, 255));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnSalir.setBounds(475, 470, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnSalir);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("Recursos\\Nombre.png"));
		lblNewLabel_1.setBounds(10, 11, 397, 84);
		frmAnalizadorLxico.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setIcon(new ImageIcon("Recursos\\Logo.png"));
		lblNewLabel_1_1.setBounds(437, 11, 203, 84);
		frmAnalizadorLxico.getContentPane().add(lblNewLabel_1_1);
		
		btnModificarAnalizador = new JButton("Modificar Analizador");
		btnModificarAnalizador.setEnabled(false);
		btnModificarAnalizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clickModificarAnalizador();
				
			}
		});
		btnModificarAnalizador.setFont(new Font("Consolas", Font.BOLD, 15));
		btnModificarAnalizador.setBackground(new Color(255, 204, 153));
		btnModificarAnalizador.setBounds(335, 111, 315, 54);
		frmAnalizadorLxico.getContentPane().add(btnModificarAnalizador);
	}
	
	/**
    * Se activa al hacer click en "Siguiente Token". Añade al histórico de tokens generados en base a la cadena introducida el siguiente token.
    * @see AnaLex.AnalizadorLexico#nextToken()
    */
	private void clickSiguienteToken() {
		
		if(!this.textField.getText().equals(this.cadenaActual)) {
			
			this.textArea.setText("");
			this.cadenaActual = this.textField.getText();
			this.AL.setCadena(Tools.codificadorLetrasEnteros(this.cadenaActual));
			
		}
		
		try {
			
			if(this.AL.hasMoreTokens()) {
				this.AL.nextToken();
			}else {
				this.btnNewButton.setEnabled(false);
			}
			
		} catch (ExcepcionDeTransicion e) {
			
			this.textArea.setText("¡Ay no! ¡Una excepción de transición! ¡Jolines!");
			e.printStackTrace();
			
		}
		this.textArea.setText(Tools.toStringTokensConLetras(this.AL.getHistorico()));
		
	}

	/**
    * Se activa al hacer click en "Completar Análisis". Añade al histórico de tokens generados en base a la cadena introducida todos los tokens restantes.
    * @see AnaLex.AnalizadorLexico#finalizarAnalisis()
    */
	private void clickCompletarAnalisis() {
		
		if(!this.textField.getText().equals(this.cadenaActual)) {
			
			this.textArea.setText("");
			this.cadenaActual = this.textField.getText();
			this.AL.setCadena(Tools.codificadorLetrasEnteros(this.cadenaActual));
			
		}
		
		this.AL.finalizarAnalisis();
		this.textArea.setText(Tools.toStringTokensConLetras(this.AL.getHistorico()));
		
	}
	
	public void DefinirAnalizador(AnalizadorLexico al) {
		this.AL = al;
	}
	
	/**
	 * Se activa al hacer click en "Nuevo Analizador". Transita al apartado de creación del analizador haciendo invisible la página principal.
	 * @see AnaLex.AnalizadorLexico#clickNuevoAnalizador()
	 */
	public void clickNuevoAnalizador() {
		
		Creación cr = new Creación(this);
		cr.setVisible(true);
		
		this.btnNewButton.setEnabled(true);
		this.btnSiguiente.setEnabled(true);
		this.btnModificarAnalizador.setEnabled(true);
		
	}
	
	/**
	 * Se activa al hacer click en "Modificar Analizador". Pasa a otra ventana donde se podrá modificar el autómata ya creado.
	 * @see AnaLex.AnalizadorLexico#clickModificarAnalizador()
	 */
	public void clickModificarAnalizador() {
		
		AutomataFinito af = this.getAutomata();
		
		Analizador an = new Analizador(af.getNumEstados(), this.simbolos, this, false);
		an.setVisible(true);
				
	}
	
	/**
	 * Método que devuelve el autómata asociado al analizador activo
	 * @return AutomataFinito es el objeto automata.
	 */
	public AutomataFinito getAutomata() {
		
		return this.AL.getAutomata();
		
	}
	
	/**
	 * Método que devuelve el analizado léxico asociado a la interfaz
	 * @return AnalizadorLexico es el objeto analizador que usa la interfaz.
	 */
	public AnalizadorLexico getAnalizador() {
		
		return this.AL;
		
	}
	
	/**
	 * Establece los simbolos del alfabeto que se están utilizando en el análisis
	 * @param simbolos es el array de String que contiene el alfabeto
	 */
	public void setSimbolos(String[] simbolos) {
		
		this.simbolos = simbolos;
		
	}
}
