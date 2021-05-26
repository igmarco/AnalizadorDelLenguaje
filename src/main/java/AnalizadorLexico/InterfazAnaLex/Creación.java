package AnalizadorLexico.InterfazAnaLex;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Creación extends JFrame {

	private JPanel contentPane;
	private JTextField tfEstados;
	private JTextField tfSimbolos;
	private int Estados;
	private String[] Simbolos;

	/**
	 * Create the frame.
	 */
	public Creación(AnalizadorLexicoInterfaz ali) {
		
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 326);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Cuántos estados componen su autómata?");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel.setBounds(21, 11, 424, 63);
		contentPane.add(lblNewLabel);
		
		tfEstados = new JTextField();
		tfEstados.setFont(new Font("Consolas", Font.PLAIN, 15));
		tfEstados.setBounds(21, 74, 374, 38);
		contentPane.add(tfEstados);
		tfEstados.setColumns(10);
		
		JLabel lblIndiqueQue = new JLabel("Indique los simbolos del alfabeto:");
		lblIndiqueQue.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblIndiqueQue.setBounds(21, 134, 424, 63);
		contentPane.add(lblIndiqueQue);
		
		tfSimbolos = new JTextField();
		tfSimbolos.setFont(new Font("Consolas", Font.PLAIN, 15));
		tfSimbolos.setBounds(21, 199, 374, 38);
		contentPane.add(tfSimbolos);
		tfSimbolos.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cada uno separado por comas.");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(21, 175, 339, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Siguiente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(102, 153, 204));
		btnNewButton.setBounds(100, 249, 214, 37);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfSimbolos.getText().isEmpty() && tfEstados.getText().isEmpty()) {
					showMessageDialog(null, "Rellene los campos");
				}else {
					try {
						
						Estados = Integer.parseInt(tfEstados.getText());
						
						Simbolos = tfSimbolos.getText().split(",");
						Arrays.sort(Simbolos);
						
						ali.setSimbolos(Simbolos);
						
						Analizador an = new Analizador(Estados, Simbolos, ali, true);
						dispose();
						an.setVisible(true);
					}catch(NumberFormatException ex) {
						showMessageDialog(null, "El número de estados debe ser un entero");
					}
				}
			}
		});
		contentPane.add(btnNewButton);
		
		
	}
	
	
	
	
	
}
