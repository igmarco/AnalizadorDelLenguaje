package AnalizadorLexico.InterfazAnaLex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AnalizadorLexico.AnaLex.AnalizadorLexico;
import AnalizadorLexico.AnaLex.AutomataFinito;
import AnalizadorLexico.AnaLex.AutomataFinitoMatriz;
import java.awt.Color;
import java.awt.Font;

public class Analizador extends JFrame {

	private JPanel contentPane;

	int Estados;
	int Alfabeto;
	int[][] matriz;
	boolean[] finales;
	AutomataFinito AF;
	AnalizadorLexico AL;
	AnalizadorLexicoInterfaz ali;

	Map<Integer, String> equivTokens;
	
	boolean equivTokensInformado, automataFinitoInformado, nuevo;

	/**
	 * Create the frame.
	 */
	public Analizador(int Estados, int Alfabeto, AnalizadorLexicoInterfaz ali, boolean nuevo) {
		
		this.ali = ali;
		this.AF = new AutomataFinitoMatriz(0, 0);
		this.Estados = Estados;
		this.Alfabeto = Alfabeto;
		this.nuevo = nuevo;

		this.equivTokens = new HashMap<>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 146);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnTokens = new JButton("Indicar Tokens");
		btnTokens.setFont(new Font("Consolas", Font.BOLD, 11));
		btnTokens.setForeground(Color.WHITE);
		btnTokens.setBackground(new Color(102, 153, 204));
		btnTokens.setEnabled((nuevo ? false : true));
		btnTokens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickTokenEstado(equivTokens);
			}
		});
		btnTokens.setBounds(301, 32, 133, 54);
		contentPane.add(btnTokens);

		JButton btnFinales = new JButton("Indicar Finales");
		btnFinales.setForeground(Color.WHITE);
		btnFinales.setFont(new Font("Consolas", Font.BOLD, 11));
		btnFinales.setBackground(new Color(102, 153, 204));
		btnFinales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clickFinales();
				btnTokens.setEnabled(true);
			}
		});
		btnFinales.setEnabled((nuevo ? false : true));
		btnFinales.setBounds(155, 32, 133, 54);
		contentPane.add(btnFinales);

		JButton btnMatriz = new JButton("Construir Matriz");
		btnMatriz.setFont(new Font("Consolas", Font.BOLD, 11));
		btnMatriz.setForeground(Color.WHITE);
		btnMatriz.setBackground(new Color(102, 153, 204));
		btnMatriz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clickConstruirMatriz();
				btnFinales.setEnabled(true);
				
			}
		});
		btnMatriz.setBounds(12, 32, 133, 54);
		contentPane.add(btnMatriz);
	}

	public Analizador(int Estados, int Alfabeto, AnalizadorLexicoInterfaz ali, boolean nuevo,
			AutomataFinito af, Map<Integer, String> equivTokens) {
		
		this.ali = ali;
		this.AF = af;
		this.Estados = Estados;
		this.Alfabeto = Alfabeto;
		this.nuevo = nuevo;
		
		this.finales = af.getFinalesBooleanList();
		this.matriz = ((AutomataFinitoMatriz) af).getTransiciones();

		this.equivTokens = equivTokens;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 146);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnTokens = new JButton("Indicar Tokens");
		btnTokens.setFont(new Font("Consolas", Font.BOLD, 11));
		btnTokens.setForeground(Color.WHITE);
		btnTokens.setBackground(new Color(102, 153, 204));
		btnTokens.setEnabled((nuevo ? false : true));
		btnTokens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickTokenEstado(equivTokens);
			}
		});
		btnTokens.setBounds(301, 32, 133, 54);
		contentPane.add(btnTokens);

		JButton btnFinales = new JButton("Indicar Finales");
		btnFinales.setForeground(Color.WHITE);
		btnFinales.setFont(new Font("Consolas", Font.BOLD, 11));
		btnFinales.setBackground(new Color(102, 153, 204));
		btnFinales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clickFinales();
				btnTokens.setEnabled(true);
			}
		});
		btnFinales.setEnabled((nuevo ? false : true));
		btnFinales.setBounds(155, 32, 133, 54);
		contentPane.add(btnFinales);

		JButton btnMatriz = new JButton("Construir Matriz");
		btnMatriz.setFont(new Font("Consolas", Font.BOLD, 11));
		btnMatriz.setForeground(Color.WHITE);
		btnMatriz.setBackground(new Color(102, 153, 204));
		btnMatriz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clickConstruirMatriz();
				btnFinales.setEnabled(true);
				
			}
		});
		btnMatriz.setBounds(12, 32, 133, 54);
		contentPane.add(btnMatriz);
		
	}

	/**
	 * Se activa al hacer click en "Indicar Tokens". Abre una ventana de diálogo en
	 * la que es posible introducir la correspondencia entre estados finales y
	 * tokens con la que trabajará el analizador léxico. En caso de que tanto el
	 * botón de "Definir Autómata" como el de "Indicar Tokens" hayan sido pulsados,
	 * activa el botón de "Construir Analizador".
	 * 
	 * @param equivTokens Diccionario con la correspondencia de estados finales y
	 *                    tokens.
	 * @see DefinirEquivTokensDialogo
	 */
	private void clickTokenEstado(Map<Integer, String> equivTokens) {
		
		DefinirEquivTokensDialogo frame;
		
		frame = new DefinirEquivTokensDialogo(equivTokens, this, this.finales, this.nuevo);
			
		frame.setVisible(true);
		frame.setModal(true);

//			equivTokens = frame.getEquivTokens();

		equivTokensInformado = true;
		

	}
	
	private void clickConstruirMatriz() {
		
		ConstruirMatriz cm = new ConstruirMatriz(AF,this, Estados, Alfabeto, this.nuevo);
		cm.setVisible(true);
		
		
	}
	
	private void clickFinales() {
		
		DefinirFinales cm = new DefinirFinales(AF,this, Estados, Alfabeto, this.nuevo);
		cm.setVisible(true);
		
		
	}

	/**
	 * Guarda el diccionario de estados finales y tokens informado en el atributo de
	 * la clase.
	 * 
	 * @param equivTokens Diccionario con la correspondencia de estados finales y
	 *                    tokens.
	 */
	public void guardarEquivTokens(Map<Integer, String> equivTokens) {

//		this.equivTokens = equivTokens;
		contruirAnalizador();
		ali.DefinirEquivTokens(equivTokens);
		ali.DefinirAnalizador(AL);
		this.dispose();

	}

	/**
	 * Guarda el autómata finito informado en el atributo de la clase.
	 * 
	 * @param AF Autómata finito.
	 * @see AutomataFinito
	 */
	public void guardarAF(AutomataFinito AF) {

		this.AF = AF;

	}
	
	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	public void setFinales(boolean[] finales) {
		this.finales = finales;
	}
	
	public void contruirAutómata() {
		
		this.AF = new AutomataFinitoMatriz(this.Estados, this.Alfabeto, this.finales, this.matriz);
	}
	
	/**
	    * Se activa al hacer click en "Construir Analizador". Crea el analizador léxico en base al autómata finito y al diccionario de estados finales y tokens introducidos en los diálogos.
	    * Habilita los botones "Siguiente Token" y "Completar Análisis".
	    * @see AnaLex.AnalizadorLexico
	    */
	public void contruirAnalizador() {
		
		this.contruirAutómata();
		
		this.AL = new AnalizadorLexico(this.AF, this.equivTokens);
		
	}

}
