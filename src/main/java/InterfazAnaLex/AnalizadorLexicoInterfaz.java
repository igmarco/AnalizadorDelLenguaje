package InterfazAnaLex;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import AnaLex.*;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;

/**
* Interfaz gráfica para el proyecto AnalizadorLéxico. Proporciona una forma de probar analizadores léxicos generalizada.
* Parte adicional de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco Pérez
* @version V2 - 28/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnaLex.AnalizadorLexico
* @see DefinirAutomataDialogo
* @see DefinirEquivTokensDialogo
*/
public class AnalizadorLexicoInterfaz {

	private JFrame frmAnalizadorLxico;
	private JTextField textField;
	JTextArea textArea;
	
	//----------------------------
	
	AnalizadorLexico AL;
	
	AutomataFinito AF;
	Map<Integer, String> equivTokens;
	
	String cadenaActual;
	
	boolean automataFinitoInformado;
	boolean equivTokensInformado;
	
	//----------------------------
	
	JButton btnNewButton, btnSiguiente, construir;

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
		
		AF = new AutomataFinitoMatriz(0, 0);
		equivTokens = new HashMap<>();
		
		cadenaActual = null;
		
		automataFinitoInformado = false;
		equivTokensInformado = false;
		
		frmAnalizadorLxico = new JFrame();
		frmAnalizadorLxico.setTitle("Analizador L\u00E9xico");
		frmAnalizadorLxico.setBounds(100, 100, 683, 469);
		frmAnalizadorLxico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalizadorLxico.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Siguiente Token");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickSiguienteToken();
				
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(39, 366, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnNewButton);
		
		btnSiguiente = new JButton("Completar An\u00E1lisis");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickCompletarAnalisis();
				
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(252, 366, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnSiguiente);
		
		textField = new JTextField();
		textField.setBounds(10, 101, 647, 50);
		frmAnalizadorLxico.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 187, 647, 168);
		frmAnalizadorLxico.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Cadena:");
		lblNewLabel.setBounds(10, 76, 46, 14);
		frmAnalizadorLxico.getContentPane().add(lblNewLabel);
		
		JLabel lblTokens = new JLabel("Tokens:");
		lblTokens.setBounds(10, 162, 46, 14);
		frmAnalizadorLxico.getContentPane().add(lblTokens);
		
		JButton btnIndicar = new JButton("Indicar Tokens");
		btnIndicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickTokenEstado(equivTokens);
				
			}
		});
		btnIndicar.setBounds(39, 11, 165, 54);
		frmAnalizadorLxico.getContentPane().add(btnIndicar);
		
		JButton btnNewButton_1_1 = new JButton("Definir Aut\u00F3mata");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickDefinirAutomata(AF);
				
			}
		});
		btnNewButton_1_1.setBounds(252, 11, 165, 54);
		frmAnalizadorLxico.getContentPane().add(btnNewButton_1_1);
		
		construir = new JButton("Construir Analizador");
		construir.setEnabled(false);
		construir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comenzamosConElAnalizador();
				
			}
		});
		construir.setBounds(465, 11, 165, 54);
		frmAnalizadorLxico.getContentPane().add(construir);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnSalir.setEnabled(false);
		btnSalir.setBounds(465, 366, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnSalir);
	}
	
	/**
    * Se activa al hacer click en "Definir Autómata". Abre una ventana de diálogo en la que es posible introducir el autómata finito con el que trabajará el analizador léxico.
    * En caso de que tanto el botón de "Definir Autómata" como el de "Indicar Tokens" hayan sido pulsados, activa el botón de "Construir Analizador".
    * @param AF Autómata finito de la interfaz.
    * @see DefinirAutomataDialogo
    */
	private void clickDefinirAutomata(AutomataFinito AF) {
		
		DefinirAutomataDialogo frame = new DefinirAutomataDialogo(AF, this);
		frame.setVisible(true);
		frame.setModal(true);
		
//		AF = frame.getAF();
		
		automataFinitoInformado = true;
		if(equivTokensInformado) construir.setEnabled(true);
		
	}
	
	/**
    * Se activa al hacer click en "Indicar Tokens". Abre una ventana de diálogo en la que es posible introducir la correspondencia entre estados finales y tokens con la que trabajará el analizador léxico.
    * En caso de que tanto el botón de "Definir Autómata" como el de "Indicar Tokens" hayan sido pulsados, activa el botón de "Construir Analizador".
    * @param equivTokens Diccionario con la correspondencia de estados finales y tokens.
    * @see DefinirEquivTokensDialogo
    */
	private void clickTokenEstado(Map<Integer, String> equivTokens) {
		
		DefinirEquivTokensDialogo frame = new DefinirEquivTokensDialogo(equivTokens, this);
		frame.setVisible(true);
		frame.setModal(true);
		
//		equivTokens = frame.getEquivTokens();
		
		equivTokensInformado= true;
		if(automataFinitoInformado) construir.setEnabled(true);
		
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
			
			this.AL.nextToken();
			
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
	
	/**
    * Se activa al hacer click en "Construir Analizador". Crea el analizador léxico en base al autómata finito y al diccionario de estados finales y tokens introducidos en los diálogos.
    * Habilita los botones "Siguiente Token" y "Completar Análisis".
    * @see AnaLex.AnalizadorLexico
    */
	private void comenzamosConElAnalizador() {
		
		this.AL = new AnalizadorLexico(AF, equivTokens);
		
		btnNewButton.setEnabled(true);
		btnSiguiente.setEnabled(true);
		
	}
	
	/**
    * Guarda el autómata finito informado en el atributo de la clase.
    * @param AF Autómata finito.
    * @see AnaLex.AutomataFinito
    */
	public void guardarAF(AutomataFinito AF) {
		
		this.AF = AF;
		
	}
	
	/**
    * Guarda el diccionario de estados finales y tokens informado en el atributo de la clase.
    * @param equivTokens Diccionario con la correspondencia de estados finales y tokens.
    */
	public void guardarEquivTokens(Map<Integer, String> equivTokens) {
		
		this.equivTokens = equivTokens;
		
	}
}
